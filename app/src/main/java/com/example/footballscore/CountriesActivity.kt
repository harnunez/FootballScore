package com.example.footballscore

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.footballscore.core.FootballScoreServiceCall
import com.example.footballscore.databinding.ActivityCountriesBinding
import com.example.footballscore.model.Country
import com.example.footballscore.model.ResponseCountries
import com.example.footballscore.services.APIFootballServices
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class CountriesActivity : AppCompatActivity() {

    private lateinit var binding : ActivityCountriesBinding
    private lateinit var adapter : CountriesAdapter
    private var listCountry = mutableListOf<Country>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCountriesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecycler()
        binding.searchCountries.setOnClickListener { searchCountries() }


    }

    private fun initRecycler(){
        adapter = CountriesAdapter(listCountry)
        binding.rvCountries.layoutManager = LinearLayoutManager(this)
        binding.rvCountries.adapter = adapter
    }


    private fun searchCountries(){
        CoroutineScope(Dispatchers.IO).launch {
            //var call:Response<ResponseCountries> = getRetrofit().create(APIFootballServices::class.java).getCountries()
            //            val respCountries: ResponseCountries? = call.body()

            val call : Response<ResponseCountries> = FootballScoreServiceCall.instance(APIFootballServices::class.java).getCountries()
            val respCountries: ResponseCountries? = call.body()

            runOnUiThread {
                if(call.isSuccessful){
                    Log.d("InitView","Entre al succes")
                    val countries  =respCountries?.response?: emptyList()
                    listCountry.clear()
                    listCountry.addAll(countries)
                    adapter.notifyDataSetChanged()
                }else{
                    Log.d("InitView","No entre al succes")

                }
            }

        }
    }

}