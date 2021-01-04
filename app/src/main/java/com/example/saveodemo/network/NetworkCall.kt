package com.example.saveodemo.network

import com.example.saveodemo.model.BaseResponse
import com.example.saveodemo.model.BaseSearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NetworkCall {

    @GET("upcoming")
    fun getUpcomingMovie(@Query("api_key") key: String): Call<BaseResponse>

    @GET("now_playing")
    fun getPlayingMovie(
        @Query("api_key") key: String,
        @Query("page") pageNo: Int
    ): Call<BaseResponse>


    @GET("{movie_id}")
    fun getDetail(
        @Path("movie_id") id: Int,
        @Query("api_key") key: String
    ): Call<BaseSearchResponse>

}
