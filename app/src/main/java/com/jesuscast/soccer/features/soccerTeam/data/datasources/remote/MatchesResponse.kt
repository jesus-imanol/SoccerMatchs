package com.jesuscast.soccer.features.soccerTeam.data.datasources.remote

import com.google.gson.annotations.SerializedName

data class MatchesResponse(
    @SerializedName("matches") val matches: List<MatchDto>
)

data class MatchDto(
    @SerializedName("id") val id: Int,
    @SerializedName("utcDate") val utcDate: String,
    @SerializedName("status") val status: String,
    @SerializedName("competition") val competition: CompetitionDto,
    @SerializedName("homeTeam") val homeTeam: MatchTeamDto,
    @SerializedName("awayTeam") val awayTeam: MatchTeamDto,
    @SerializedName("score") val score: ScoreDto?
)

data class CompetitionDto(
    @SerializedName("name") val name: String,
    @SerializedName("emblem") val emblem: String?
)

data class MatchTeamDto(
    @SerializedName("name") val name: String,
    @SerializedName("crest") val crest: String
)

data class ScoreDto(
    @SerializedName("fullTime") val fullTime: TimeDto?
)

data class TimeDto(
    @SerializedName("home") val home: Int?,
    @SerializedName("away") val away: Int?
)
