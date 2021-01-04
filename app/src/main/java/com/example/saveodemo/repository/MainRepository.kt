package com.example.saveodemo.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.saveodemo.model.BaseResponse
import com.example.saveodemo.network.RestService
import retrofit2.Call
import retrofit2.Response

class MainRepository {
    var retrofitObj = RestService.rest
    var myApiRepo: MainRepository? = null
    fun getFeaturedData(): MutableLiveData<BaseResponse> {
        val listData = MutableLiveData<BaseResponse>()
        retrofitObj.getUpcomingMovie("a481f1eb249ec2fb6a0466aa51354515").enqueue(object :
            retrofit2.Callback<BaseResponse> {
            override fun onResponse(call: Call<BaseResponse>, response: Response<BaseResponse>) {
                if (response.isSuccessful) {
                    listData.value = response.body()!!
                }
            }

            override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                Log.d("Issue in APi", "onFailure: $t")
            }

        })

        return listData
    }


    fun getInstance(): MainRepository? {
        if (myApiRepo == null) {
            run {
                myApiRepo = MainRepository()
            }
        }
        return myApiRepo
    }


}