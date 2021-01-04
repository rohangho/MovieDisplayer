package com.example.saveodemo.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RestService {
    val rest: NetworkCall
        get() {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/movie/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(NetworkCall::class.java)
        }
}