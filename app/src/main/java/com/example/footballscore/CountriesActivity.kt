package com.example.footballscore

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.footballscore.core.FootballScoreInterceptor
import com.example.footballscore.databinding.ActivityCountriesBinding
import com.example.footballscore.model.Country
import com.example.footballscore.model.ResponseCountries
import com.example.footballscore.services.APIFootballServices
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CountriesActivity : AppCompatActivity() {

    private lateinit var binding : ActivityCountriesBinding
    private lateinit var adapter : CountriesAdapter
    private var listDog = mutableListOf<Country>()

    private val okHttpLoggingInterceptor:
            Interceptor
    get(){
        return HttpLoggingInterceptor().apply { setLevel(HttpLoggingInterceptor.Level.BODY) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCountriesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecycler()
        binding.searchCountries.setOnClickListener { searchCountries() }


    }

    private fun initRecycler(){
        adapter = CountriesAdapter(listDog)
        binding.rvCountries.layoutManager = LinearLayoutManager(this)
        binding.rvCountries.adapter = adapter
    }

    private fun getRetrofit():Retrofit{


        var httClientBuilder = OkHttpClient.Builder()
                            .addInterceptor(FootballScoreInterceptor())
                            .addInterceptor(okHttpLoggingInterceptor)
                            .build()



        return Retrofit.Builder()
            .baseUrl("https://v3.football.api-sports.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(httClientBuilder)
            .build()
    }

    private fun searchCountries(){
        CoroutineScope(Dispatchers.IO).launch {
            var call:Response<ResponseCountries> = getRetrofit().create(APIFootballServices::class.java).getCountries()
            val respCountries: ResponseCountries? = call.body()

            runOnUiThread {
                if(call.isSuccessful){
                    Log.d("InitView","Entre al succes")
                    val countries  =respCountries?.response?: emptyList()
                    listDog.clear()
                    listDog.addAll(countries)
                    adapter.notifyDataSetChanged()
                }else{
                    Log.d("InitView","No entre al succes")

                }
            }

        }
    }

}