package com.example.footballscore.team

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.footballscore.team.utils.NavigationModule
import com.example.footballscore.core.FootballScoreServiceCall
import com.example.footballscore.team.model.Country
import com.example.footballscore.team.model.ResponseCountries
import com.example.footballscore.team.model.ResponseSeason
import com.example.footballscore.team.repository.FootballSoccerRepository
import com.example.footballscore.team.services.APIFootballServices
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class FootballScoreViewModel internal constructor(
    private val repository: FootballSoccerRepository
): ViewModel(){

    private val _countries = MutableLiveData<List<Country>>()
    val countries : LiveData<List<Country>> = _countries

    private val _seasons = MutableLiveData<List<Int>>()
    val seasons: LiveData<List<Int>> = _seasons

    private val _seasonSelected = MutableLiveData<Int>()
    val seasonSelected: LiveData<Int> = _seasonSelected


    private val _countrySelected = MutableLiveData<Country>()
    val countrySelected: LiveData<Country> = _countrySelected

    private val _navigation = MutableLiveData<NavigationModule>()
    val navigation : LiveData<NavigationModule> = _navigation

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> = _isLoading

    fun init (){
        if(countries.value.isNullOrEmpty()){
            searchCountries()
        }
    }

    fun searchCountries(){

        _isLoading.postValue(true)

        CoroutineScope(Dispatchers.IO).launch {
            val call : Response<ResponseCountries> = FootballScoreServiceCall
                .instance(APIFootballServices::class.java)
                .getCountries()

            val respCountries: ResponseCountries? = call.body()
            if(call.isSuccessful){
                Log.d("InitView","Entre al succes")
                _isLoading.postValue(false)
                val countries  =respCountries?.response?: emptyList()
                _countries.postValue(countries)
                setNavigate(NavigationModule.COUNTRIES_SCREEN)

            }else{
                Log.d("InitView","No entre al succes")
                _isLoading.postValue(false)
            }

        }
    }

    fun searchSessions(){
        CoroutineScope(Dispatchers.IO).launch {
            val call: Response<ResponseSeason> = FootballScoreServiceCall
                .instance(APIFootballServices::class.java).getSessions()

            val respSeason = call.body()

            if(call.isSuccessful && call.code() == 200){
                Log.d("Season","Succes Season")
                val listSeason = respSeason?.response?: emptyList()
                _seasons.postValue(listSeason)
            }else{
                Log.d("Season","Denied Season")
            }

        }
    }

    fun setSeasonSelected(season:Int){
        Log.d("Season Selected",season.toString())
        _seasonSelected.postValue(season)
    }

    fun setCountrySelected(country:Country){
        Log.d("Country Selected", country.code?:"NoCountry")
        _countrySelected.postValue(country)
    }

    fun setNavigate(navigationModule: NavigationModule){
        _navigation.postValue(navigationModule)
    }

}