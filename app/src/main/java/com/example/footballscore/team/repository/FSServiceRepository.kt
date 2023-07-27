package com.example.footballscore.team.repository

import com.example.footballscore.core.FootballScoreServiceCall

class FSServiceRepository(
    private val service: FootballScoreServiceCall
) {



    companion object{

        private var INSTANCE:FSServiceRepository? = null

        fun getFSServiceRepository(service: FootballScoreServiceCall) =
            INSTANCE?: synchronized(this){
                INSTANCE?: FSServiceRepository(service).also { INSTANCE=it }
            }
    }
}