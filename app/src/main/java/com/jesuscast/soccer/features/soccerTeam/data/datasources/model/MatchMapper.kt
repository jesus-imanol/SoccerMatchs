package com.jesuscast.soccer.features.soccerTeam.data.datasources.model

import com.jesuscast.soccer.features.soccerTeam.data.datasources.remote.MatchDto
import com.jesuscast.soccer.features.soccerTeam.domain.entities.Match

fun MatchDto.toDomain(): Match {
    return Match(
        id = id,
        homeTeamName = homeTeam.name,
        awayTeamName = awayTeam.name,
        homeTeamCrest = homeTeam.crest,
        awayTeamCrest = awayTeam.crest,
        competitionName = competition.name,
        utcDate = utcDate,
        status = status,
        homeScore = score?.fullTime?.home,
        awayScore = score?.fullTime?.away
    )
}
