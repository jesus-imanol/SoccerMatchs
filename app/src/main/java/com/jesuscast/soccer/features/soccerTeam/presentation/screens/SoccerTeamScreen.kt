package com.jesuscast.soccer.features.soccerTeam.presentation.screens

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jesuscast.soccer.features.soccerTeam.presentation.components.MatchCard
import com.jesuscast.soccer.features.soccerTeam.presentation.viewmodels.SoccerTeamViewModel
import com.jesuscast.soccer.features.soccerTeam.presentation.viewmodels.SoccerTeamViewModelFactory
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

private enum class SelectedDay {
    TODAY, TOMORROW, CUSTOM
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SoccerTeamsScreen(
    factory: SoccerTeamViewModelFactory
) {
    val viewModel: SoccerTeamViewModel = viewModel(factory = factory)
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    var selectedDay by remember { mutableStateOf(SelectedDay.TODAY) }
    var showDatePicker by remember { mutableStateOf(false) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Matches", fontWeight = FontWeight.Medium) },
                actions = {
                    IconButton(onClick = { showDatePicker = true }) {
                        Icon(Icons.Default.DateRange, contentDescription = "Select date")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                if (selectedDay == SelectedDay.TODAY) {
                    Button(onClick = {  }) { Text("Today") }
                } else {
                    OutlinedButton(onClick = {
                        selectedDay = SelectedDay.TODAY
                        viewModel.loadTodayMatches()
                    }) { Text("Today") }
                }

                if (selectedDay == SelectedDay.TOMORROW) {
                    Button(onClick = { /* Already selected */ }) { Text("Tomorrow") }
                } else {
                    OutlinedButton(onClick = {
                        selectedDay = SelectedDay.TOMORROW
                        viewModel.loadTomorrowMatches()
                    }) { Text("Tomorrow") }
                }

                if (selectedDay == SelectedDay.CUSTOM) {
                    Button(onClick = { showDatePicker = true }) {
                        Text(uiState.selectedDate.format(DateTimeFormatter.ofPattern("dd/MM")))
                    }
                } else {
                    OutlinedButton(onClick = { showDatePicker = true }) {
                        Text("Custom")
                    }
                }
            }

            // Competition filters
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState())
                    .padding(horizontal = 8.dp, vertical = 4.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                uiState.availableCompetitions.forEach { competition ->
                    FilterChip(
                        selected = when {
                            competition.code.isEmpty() -> uiState.selectedCompetition == null
                            else -> uiState.selectedCompetition == competition.code
                        },
                        onClick = {
                            val newCompetition = if (competition.code.isEmpty()) null else competition.code
                            viewModel.updateCompetition(newCompetition)
                        },
                        label = { Text(competition.name) }
                    )
                }
            }

            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                if (uiState.isLoading) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                } else if (uiState.error != null) {
                    Text(
                        text = uiState.error!!,
                        color = Color.Red,
                        modifier = Modifier.align(Alignment.Center)
                    )
                } else if (uiState.matches.isEmpty()) {
                    Text(
                        text = "No matches found for this date",
                        modifier = Modifier.align(Alignment.Center)
                    )
                } else {
                    LazyColumn(
                        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp)
                    ) {
                        items(uiState.matches) { match ->
                            val zonedDateTime = ZonedDateTime.parse(match.utcDate)
                            val localTime = zonedDateTime.toLocalTime()
                            val formattedTime = localTime.format(DateTimeFormatter.ofPattern("HH:mm"))

                            MatchCard(
                                matchId = match.id,
                                homeTeamName = match.homeTeamName,
                                awayTeamName = match.awayTeamName,
                                homeTeamCrest = match.homeTeamCrest,
                                awayTeamCrest = match.awayTeamCrest,
                                competitionName = match.competitionName,
                                matchTime = formattedTime,
                                homeScore = match.homeScore,
                                awayScore = match.awayScore,
                                status = match.status
                            )
                        }
                    }
                }
            }
        }
    }

    if (showDatePicker) {
        val datePickerState = rememberDatePickerState(
            initialSelectedDateMillis = uiState.selectedDate.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli()
        )

        DatePickerDialog(
            onDismissRequest = { showDatePicker = false },
            confirmButton = {
                TextButton(onClick = {
                    datePickerState.selectedDateMillis?.let { millis ->
                        val selectedDate = Instant.ofEpochMilli(millis)
                            .atZone(ZoneId.systemDefault())
                            .toLocalDate()
                        selectedDay = SelectedDay.CUSTOM
                        viewModel.updateDate(selectedDate)
                    }
                    showDatePicker = false
                }) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDatePicker = false }) {
                    Text("Cancel")
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }
}
