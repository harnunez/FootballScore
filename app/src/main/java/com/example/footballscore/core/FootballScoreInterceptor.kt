package com.example.footballscore.core

import okhttp3.Interceptor
import okhttp3.Response

class FootballScoreInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        //TODO("Not yet implemented")
        var request = chain.request()
        request.newBuilder()?.addHeader("x-rapidapi-host","v3.football.api-sports.io")
                            ?.addHeader("x-rapidapi-key","8aa4f136dc8d2155f3488effcd8044b9")
                            ?.build()

        return chain.proceed(request)
    }
}