package com.jesuscast.soccer.core.di

import android.content.Context
import com.jesuscast.soccer.core.network.SoccerTeamApi
import com.jesuscast.soccer.features.soccerTeam.data.repositories.SoccerTeamRepositoryImpl
import com.jesuscast.soccer.features.soccerTeam.domain.repositories.SoccerTeamRepository
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class AppContainer(context: Context) {

    private val authInterceptor = Interceptor { chain ->
        val newRequest = chain.request().newBuilder()
            .addHeader("X-Auth-Token", "71f0c9c4b2a6426fa1b83cf7add9b32b")
            .build()
        chain.proceed(newRequest)
    }

    // Hacer público el okHttpClient para usarlo en Coil
    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(authInterceptor)
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()

    private val retrofit : Retrofit = Retrofit.Builder()
        .baseUrl("https://api.football-data.org/v4/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    val soccerTeamApi : SoccerTeamApi by lazy {
        retrofit.create(SoccerTeamApi::class.java)
    }

    val soccerTeamRepository : SoccerTeamRepository by lazy {
        SoccerTeamRepositoryImpl(soccerTeamApi)
    }



}