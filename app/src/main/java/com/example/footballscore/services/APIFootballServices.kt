package com.example.footballscore.services

import com.example.footballscore.model.ResponseCountries
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface APIFootballServices {

    //@Headers("Accept: application/json", "Content-Type: application/json")
    @Headers("x-rapidapi-host:v3.football.api-sports.io", "x-rapidapi-key:8aa4f136dc8d2155f3488effcd8044b9")
    @GET("countries")
    suspend fun getCountries():Response<ResponseCountries>

}