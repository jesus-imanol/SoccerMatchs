package com.jesuscast.soccer.features.soccerTeam.domain.usecases

import com.jesuscast.soccer.features.soccerTeam.domain.entities.Competition

class GetAvailableCompetitionsUseCase {
    operator fun invoke(): List<Competition> {
        return listOf(
            Competition("All", ""),
            Competition("Premier League", "PL"),
            Competition("La Liga", "PD"),
            Competition("Bundesliga", "BL1"),
            Competition("Serie A", "SA"),
            Competition("Ligue 1", "FL1"),
            Competition("Champions League", "CL"),
            Competition("Europa League", "EL")
        )
    }
}

