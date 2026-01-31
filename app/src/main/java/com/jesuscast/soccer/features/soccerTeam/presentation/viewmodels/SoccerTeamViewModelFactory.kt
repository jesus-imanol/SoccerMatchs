package com.jesuscast.soccer.features.soccerTeam.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jesuscast.soccer.features.soccerTeam.domain.usecases.GetAvailableCompetitionsUseCase
import com.jesuscast.soccer.features.soccerTeam.domain.usecases.GetMatchesUseCase
import com.jesuscast.soccer.features.soccerTeam.domain.usecases.GetSoccerTeamsUseCase

class SoccerTeamViewModelFactory(
    private val getSoccerTeamsUseCase: GetSoccerTeamsUseCase,
    private val getMatchesUseCase: GetMatchesUseCase,
    private val getAvailableCompetitionsUseCase: GetAvailableCompetitionsUseCase
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(SoccerTeamViewModel::class.java) -> {
                SoccerTeamViewModel(
                    getSoccerTeamsUseCase,
                    getMatchesUseCase,
                    getAvailableCompetitionsUseCase
                ) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    }

}

