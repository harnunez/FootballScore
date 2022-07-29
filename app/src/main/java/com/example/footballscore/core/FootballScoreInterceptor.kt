package com.example.footballscore.core

import okhttp3.Interceptor
import okhttp3.Response

class FootballScoreInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val originBuilder = chain.request()
        val requestBuilder = originBuilder.newBuilder()
            .header("x-rapidapi-host","v3.football.api-sports.io")
            .header("x-rapidapi-key","8aa4f136dc8d2155f3488effcd8044b9")

        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}