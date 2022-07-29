package com.example.footballscore.core

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object FootballScoreServiceCall {

    private const val BASE_URL = "https://v3.football.api-sports.io/"

    private var retrofit:Retrofit?=null
    private val okHttpLoggingInterceptor: Interceptor
        get(){
            return HttpLoggingInterceptor().apply { setLevel(HttpLoggingInterceptor.Level.BODY) }
        }


    fun <S> instance(serviceClass: Class<S>): S{
        return getRetrofit(serviceClass)
    }

    private fun <S> getRetrofit(serviceClass: Class<S>):S {

        val httClientBuilder = OkHttpClient.Builder()
            .addInterceptor(FootballScoreInterceptor())
            .addInterceptor(okHttpLoggingInterceptor)
            .build()

        val retrofitBuilder = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httClientBuilder)

        retrofit = retrofitBuilder.build()

        return retrofit!!.create(serviceClass)

    }


}