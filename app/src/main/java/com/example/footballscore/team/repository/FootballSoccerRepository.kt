package com.example.footballscore.team.repository

class FootballSoccerRepository private constructor(
    private val fsRepo: FSServiceRepository
) {

    fun searchCountries() = fsRepo.getCountries()


    companion object {
        private var instance: FootballSoccerRepository? = null

        fun getInstance(service: FSServiceRepository) =
            instance ?: synchronized(this) {
                instance ?: FootballSoccerRepository(service).also { instance = it }
            }
    }
}