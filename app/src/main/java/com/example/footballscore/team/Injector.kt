package com.example.footballscore.team

import com.example.footballscore.core.FootballScoreServiceCall
import com.example.footballscore.team.repository.FootballSoccerRepository

interface ViewModelFactoryProvider {
    fun provideFootballScoreViewModelfactory(): FootballScoreVMFactory
}

val Injector: ViewModelFactoryProvider
    get() = currentInjector

private object DefaultViewModelProvider : ViewModelFactoryProvider {

    private fun getFootballSoccerRepo(): FootballSoccerRepository {
        return FootballSoccerRepository.getInstance(
            getService()
        )
    }

    /** Parametros del repo
     * Servicio retrofit
     **/
     private fun getService() = FootballScoreServiceCall

    /**
     * Metodo llamado desde viewmodel Provider en Activity o fragment**/
    override fun provideFootballScoreViewModelfactory(): FootballScoreVMFactory {
        val repository = getFootballSoccerRepo()
        return FootballScoreVMFactory(repository)
    }
}


private var currentInjector: ViewModelFactoryProvider = DefaultViewModelProvider
