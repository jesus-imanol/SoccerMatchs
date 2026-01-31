package com.jesuscast.soccer.features.soccerTeam.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jesuscast.soccer.features.soccerTeam.domain.usecases.GetAvailableCompetitionsUseCase
import com.jesuscast.soccer.features.soccerTeam.domain.usecases.GetMatchesUseCase
import com.jesuscast.soccer.features.soccerTeam.domain.usecases.GetSoccerTeamsUseCase
import com.jesuscast.soccer.features.soccerTeam.presentation.screens.SoccerTeamState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class SoccerTeamViewModel(
    private val getSoccerTeamsUseCase: GetSoccerTeamsUseCase,
    private val getMatchesUseCase: GetMatchesUseCase,
    private val getAvailableCompetitionsUseCase: GetAvailableCompetitionsUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(SoccerTeamState())
    val uiState = _uiState.asStateFlow()

    init {
        loadAvailableCompetitions()
        loadMatches()
    }

    private fun loadAvailableCompetitions() {
        val competitions = getAvailableCompetitionsUseCase()
        _uiState.update { it.copy(availableCompetitions = competitions) }
    }

    fun loadTodayMatches() {
        updateDate(LocalDate.now())
    }

    fun loadTomorrowMatches() {
        updateDate(LocalDate.now().plusDays(1))
    }

    fun updateDate(date: LocalDate) {
        _uiState.update { it.copy(selectedDate = date) }
        loadMatches()
    }

    fun updateCompetition(competition: String?) {
        _uiState.update { it.copy(selectedCompetition = competition) }
        loadMatches()
    }

    private fun loadMatches() {
        _uiState.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            try {
                val dateString = _uiState.value.selectedDate.format(DateTimeFormatter.ISO_LOCAL_DATE)
                val competitions = _uiState.value.selectedCompetition
                val matches = getMatchesUseCase(date = dateString, competitions = competitions)
                _uiState.update { it.copy(isLoading = false, matches = matches, error = null) }
            } catch (e: Exception) {
                _uiState.update { it.copy(isLoading = false, error = e.message) }
            }
        }
    }
}
