package com.example.footballscore.team

import com.example.footballscore.core.FootballScoreServiceCall
import com.example.footballscore.team.repository.FSServiceRepository
import com.example.footballscore.team.repository.FootballSoccerRepository
import retrofit2.Call

interface ViewModelFactoryProvider {
    fun provideFootballScoreViewModelfactory(): FootballScoreVMFactory
}

val Injector: ViewModelFactoryProvider
    get() = currentInjector

private object DefaultViewModelProvider : ViewModelFactoryProvider {

    val fsRepository = getFSServiceRepository(getServiceCall())


    private fun getFootballSoccerRepo(fsRepository: FSServiceRepository)
    : FootballSoccerRepository {
        return FootballSoccerRepository.getInstance(fsRepository)
    }

    /**
     * Parametros del repo. Servicio retrofit
     */
     private fun getServiceCall() = FootballScoreServiceCall

    private fun getFSServiceRepository(fsSeviceCall: FootballScoreServiceCall)=
        FSServiceRepository.getFSServiceRepository(fsSeviceCall)

    /**
     * Metodo llamado desde viewmodel Provider en Activity o fragment**/
    override fun provideFootballScoreViewModelfactory(): FootballScoreVMFactory {
        val repository = getFootballSoccerRepo(fsRepository)
        return FootballScoreVMFactory(repository)
    }
}


private var currentInjector: ViewModelFactoryProvider = DefaultViewModelProvider
