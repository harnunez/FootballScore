package com.example.footballscore.team.repository

import com.example.footballscore.core.FootballScoreServiceCall

class FootballSoccerRepository private constructor(
    private val service: FootballScoreServiceCall
) {



    companion object {
        private var instance: FootballSoccerRepository? = null

        fun getInstance(service: FootballScoreServiceCall) =
            instance ?: synchronized(this){
                instance ?: FootballSoccerRepository(service).also { instance = it }
            }
    }
}