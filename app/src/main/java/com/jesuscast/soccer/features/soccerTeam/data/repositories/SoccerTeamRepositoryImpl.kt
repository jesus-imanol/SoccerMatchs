package com.jesuscast.soccer.features.soccerTeam.data.repositories

import com.jesuscast.soccer.core.network.SoccerTeamApi
import com.jesuscast.soccer.features.soccerTeam.data.datasources.model.toDomain
import com.jesuscast.soccer.features.soccerTeam.domain.entities.Match
import com.jesuscast.soccer.features.soccerTeam.domain.entities.SoccerTeam
import com.jesuscast.soccer.features.soccerTeam.domain.repositories.SoccerTeamRepository

class SoccerTeamRepositoryImpl(
    private val api : SoccerTeamApi
) : SoccerTeamRepository {
    override suspend fun getSoccerTeams(): List<SoccerTeam> {
        val response = api.getSoccerTeams()
        return response.teams.map { it.toDomain() }
    }

    override suspend fun getMatches(date: String, competitions: String?, status: String?): List<Match> {
        val response = api.getMatches(date, competitions, status)
        return response.matches.map { it.toDomain() }
    }
}