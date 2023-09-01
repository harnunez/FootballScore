package com.example.footballscore.team.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import ar.com.galicia.core.services.ServiceResult
import com.example.footballscore.core.FootballScoreServiceCall
import com.example.footballscore.team.model.ResponseCountries
import com.example.footballscore.team.services.APIFootballServices
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class FSServiceRepository(
    private val networkService: FootballScoreServiceCall,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    private val service: APIFootballServices
            by lazy { networkService.instance(APIFootballServices::class.java) }


    fun getCountries() = liveData(ioDispatcher) {
        Log.d("repos", "countries")
        ServiceResult.createCall({ service.getCountries() }, {
            it.let { success ->
                val mutableSucces = MutableLiveData<ServiceResult<ResponseCountries>>().apply {
                    postValue(ServiceResult.success(success))
                }
                emitSource(mutableSucces)
            }

        }, {
            it.let { error ->
                val mutableError: MutableLiveData<ServiceResult<ResponseCountries>> =
                    MutableLiveData()
                mutableError.postValue(ServiceResult.error())
                emitSource(mutableError)
            }
        })
    }

    companion object {

        private var INSTANCE: FSServiceRepository? = null

        fun getFSServiceRepository(service: FootballScoreServiceCall) =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: FSServiceRepository(service).also { INSTANCE = it }
            }
    }
}