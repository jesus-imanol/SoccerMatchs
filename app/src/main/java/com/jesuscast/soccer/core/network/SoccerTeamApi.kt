package com.jesuscast.soccer.core.network

import com.jesuscast.soccer.features.soccerTeam.data.datasources.remote.MatchesResponse
import com.jesuscast.soccer.features.soccerTeam.data.datasources.remote.SoccerTeamResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface SoccerTeamApi {
    @GET("teams")
    suspend fun getSoccerTeams(): SoccerTeamResponse

    @GET("matches")
    suspend fun getMatches(
        @Query("date") date: String,
        @Query("competitions") competitions: String? = null,
        @Query("status") status: String? = null
    ): MatchesResponse
}