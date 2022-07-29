package com.example.footballscore.services

import com.example.footballscore.model.ResponseCountries
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface APIFootballServices {

    @GET("countries")
    suspend fun getCountries():Response<ResponseCountries>

}