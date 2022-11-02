package com.example.footballscore.team.services

import com.example.footballscore.team.model.ResponseCountries
import com.example.footballscore.team.model.ResponseSeason
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface APIFootballServices {

    @GET("countries")
    suspend fun getCountries():Response<ResponseCountries>

    @GET("leagues/seasons")
    suspend fun getSessions():Response<ResponseSeason>

}