package com.jesuscast.soccer.features.soccerTeam.di

import com.jesuscast.soccer.core.di.AppContainer
import com.jesuscast.soccer.features.soccerTeam.domain.usecases.GetMatchesUseCase
import com.jesuscast.soccer.features.soccerTeam.domain.usecases.GetSoccerTeamsUseCase
import com.jesuscast.soccer.features.soccerTeam.presentation.viewmodels.SoccerTeamViewModelFactory

class SoccerTeamModule( private val appContainer: AppContainer) {

    private fun provideGetSoccerTeamsUseCase(): GetSoccerTeamsUseCase {
        return GetSoccerTeamsUseCase(appContainer.soccerTeamRepository)
    }

    private fun provideGetMatchesUseCase(): GetMatchesUseCase {
        return GetMatchesUseCase(appContainer.soccerTeamRepository)
    }

    fun provideSoccerTeamViewModelFactory(): SoccerTeamViewModelFactory {
        return SoccerTeamViewModelFactory(
            provideGetSoccerTeamsUseCase(),
            provideGetMatchesUseCase()
        )
    }
}
