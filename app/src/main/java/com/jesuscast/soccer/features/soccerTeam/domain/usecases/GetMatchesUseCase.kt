package com.jesuscast.soccer.features.soccerTeam.domain.usecases

import com.jesuscast.soccer.features.soccerTeam.domain.entities.Match
import com.jesuscast.soccer.features.soccerTeam.domain.repositories.SoccerTeamRepository

class GetMatchesUseCase(private val repository: SoccerTeamRepository) {
    suspend operator fun invoke(date: String, competitions: String? = null, status: String? = null): List<Match> {
        return repository.getMatches(date, competitions, status)
    }
}