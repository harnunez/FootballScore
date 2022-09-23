package com.example.footballscore.team

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.footballscore.NavigationModule
import com.example.footballscore.core.FootballScoreServiceCall
import com.example.footballscore.team.model.Country
import com.example.footballscore.team.model.ResponseCountries
import com.example.footballscore.team.services.APIFootballServices
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class FootballScoreViewModel : ViewModel(){


    private val _countries = MutableLiveData<List<Country>>()
    val countries : LiveData<List<Country>> = _countries

    private val _navigation= MutableLiveData<NavigationModule>()
    val navigation : LiveData<NavigationModule> = _navigation


    fun init (){
        if(countries.value.isNullOrEmpty()){
            searchCountries()
        }
    }

    fun searchCountries(){

        CoroutineScope(Dispatchers.IO).launch {
            val call : Response<ResponseCountries> = FootballScoreServiceCall
                .instance(APIFootballServices::class.java)
                .getCountries()

            val respCountries: ResponseCountries? = call.body()

            if(call.isSuccessful){
                Log.d("InitView","Entre al succes")
                val countries  =respCountries?.response?: emptyList()
                _countries.postValue(countries)
                setNavigate(NavigationModule.COUNTRIES_SCREEN)

            }else{
                Log.d("InitView","No entre al succes")

            }

        }
    }

    fun setNavigate(navigationModule: NavigationModule){
        _navigation.postValue(navigationModule)
    }

}