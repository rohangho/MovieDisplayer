package com.example.saveodemo.network

import com.example.saveodemo.model.BaseResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkCall {

    @GET("upcoming")
    fun getUpcomingMovie(@Query("api_key") key: String): Call<BaseResponse>

    @GET("now_playing")
    fun getPlayingMovie(
        @Query("api_key") key: String,
        @Query("page") pageNo: Int
    ): Call<BaseResponse>

}
