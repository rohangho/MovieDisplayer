package com.example.saveodemo.datasoursce

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.example.saveodemo.model.BaseResponse
import com.example.saveodemo.model.Result
import com.example.saveodemo.network.RestService
import retrofit2.Call
import retrofit2.Response

class PlayingMoviesDataSoursce : PageKeyedDataSource<Int, Result>() {

    var retrofitObj = RestService.rest
    val key = "a481f1eb249ec2fb6a0466aa51354515"

    override fun loadInitial(
            params: LoadInitialParams<Int>,
            callback: LoadInitialCallback<Int, Result>
    ) {
        retrofitObj.getPlayingMovie(key, 1).enqueue(object :
                retrofit2.Callback<BaseResponse> {
            override fun onResponse(call: Call<BaseResponse>, response: Response<BaseResponse>) {
                if (response.isSuccessful) {
                    callback.onResult(response.body()!!.results!!, null, 1)
                }
            }

            override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                Log.d("Issue in APi", "onFailure: $t")
            }

        })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Result>) {
        TODO("Not yet implemented")
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Result>) {
        retrofitObj.getPlayingMovie(key, params.key).enqueue(object :
                retrofit2.Callback<BaseResponse> {
            override fun onResponse(call: Call<BaseResponse>, response: Response<BaseResponse>) {
                if (response.isSuccessful) {
                    callback.onResult(response.body()!!.results!!, params.key + 1)
                }
            }

            override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                Log.d("Issue in APi", "onFailure: $t")
            }

        })
    }
}