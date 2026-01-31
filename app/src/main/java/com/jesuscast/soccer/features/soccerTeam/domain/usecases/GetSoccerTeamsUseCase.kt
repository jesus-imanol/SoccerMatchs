package com.jesuscast.soccer.features.soccerTeam.domain.usecases

import com.jesuscast.soccer.features.soccerTeam.domain.entities.SoccerTeam
import com.jesuscast.soccer.features.soccerTeam.domain.repositories.SoccerTeamRepository

class GetSoccerTeamsUseCase(
    private val soccerTeamRepository: SoccerTeamRepository
) {
    suspend operator fun invoke() : Result<List<SoccerTeam>> {
        return try {
                val soccerTeams = soccerTeamRepository.getSoccerTeams()
                val filteredSoccerTeam = soccerTeams.filter { it.name.isNotBlank() }

                if(filteredSoccerTeam.isEmpty()){
                    Result.failure(Exception("No Soccer Teams Found"))
                } else {
                    Result.success(filteredSoccerTeam)
                }
        }catch (e: Exception) {
            Result.failure(e)
        }
    }

}