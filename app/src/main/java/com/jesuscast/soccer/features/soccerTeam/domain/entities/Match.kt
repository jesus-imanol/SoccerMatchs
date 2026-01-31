package com.jesuscast.soccer.features.soccerTeam.domain.entities

data class Match(
    val id: Int,
    val homeTeamName: String,
    val awayTeamName: String,
    val homeTeamCrest: String,
    val awayTeamCrest: String,
    val competitionName: String,
    val utcDate: String,
    val status: String,
    val homeScore: Int?,
    val awayScore: Int?
)
