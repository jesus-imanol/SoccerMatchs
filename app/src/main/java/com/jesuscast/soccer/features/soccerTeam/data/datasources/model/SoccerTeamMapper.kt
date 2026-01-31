package com.jesuscast.soccer.features.soccerTeam.data.datasources.model

import com.jesuscast.soccer.features.soccerTeam.data.datasources.remote.SoccerTeamDto
import com.jesuscast.soccer.features.soccerTeam.domain.entities.SoccerTeam

fun SoccerTeamDto.toDomain() : SoccerTeam{
    return SoccerTeam(
        id = this.id,
        name = this.name,
        shortname = this.shortName,
        logoUrl = this.crest
    )
}