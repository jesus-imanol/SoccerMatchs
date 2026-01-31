package com.jesuscast.soccer.features.soccerTeam.presentation.screens

import com.jesuscast.soccer.features.soccerTeam.domain.entities.Competition
import com.jesuscast.soccer.features.soccerTeam.domain.entities.Match
import com.jesuscast.soccer.features.soccerTeam.domain.entities.SoccerTeam
import java.time.LocalDate

data class SoccerTeamState(
    val isLoading: Boolean = false,
    val soccerTeams: List<SoccerTeam> = emptyList(),
    val matches: List<Match> = emptyList(),
    val error: String? = null,
    val isRefreshing: Boolean = false,
    val selectedDate: LocalDate = LocalDate.now(),
    val selectedCompetition: String? = null,
    val availableCompetitions: List<Competition> = emptyList()
)
