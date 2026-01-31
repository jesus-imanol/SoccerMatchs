package com.jesuscast.soccer.features.soccerTeam.domain.repositories

import com.jesuscast.soccer.features.soccerTeam.domain.entities.Match
import com.jesuscast.soccer.features.soccerTeam.domain.entities.SoccerTeam

interface SoccerTeamRepository {
    suspend fun getSoccerTeams(): List<SoccerTeam>
    suspend fun getMatches(date: String, competitions: String? = null, status: String? = null): List<Match>
}