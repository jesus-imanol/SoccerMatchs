package com.jesuscast.soccer.features.soccerTeam.data.datasources.remote

data class SoccerTeamResponse(
    val count: Int,
    val teams: List<SoccerTeamDto>
)

data class SoccerTeamDto(
    val id: Int,
    val name: String,
    val shortName: String,
    val crest: String
)