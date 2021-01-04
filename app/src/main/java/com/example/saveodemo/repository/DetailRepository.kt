package com.example.saveodemo.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.saveodemo.model.BaseSearchResponse
import com.example.saveodemo.network.RestService
import retrofit2.Call
import retrofit2.Response

class DetailRepository {
    var retrofitObj = RestService.rest
    var myApiRepo: DetailRepository? = null

    fun getSearchData(id: Int): MutableLiveData<BaseSearchResponse> {
        val listData = MutableLiveData<BaseSearchResponse>()
        retrofitObj.getDetail(id, "a481f1eb249ec2fb6a0466aa51354515").enqueue(object :
            retrofit2.Callback<BaseSearchResponse> {
            override fun onResponse(
                call: Call<BaseSearchResponse>,
                response: Response<BaseSearchResponse>
            ) {
                if (response.isSuccessful) {
                    listData.value = response.body()!!
                }
            }

            override fun onFailure(call: Call<BaseSearchResponse>, t: Throwable) {
                Log.d("Issue in APi", "onFailure: $t")
            }

        })

        return listData
    }

    fun getInstance(): DetailRepository? {
        if (myApiRepo == null) {
            run {
                myApiRepo = DetailRepository()
            }
        }
        return myApiRepo
    }


}