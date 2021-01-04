package com.example.saveodemo.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.saveodemo.model.BaseResponse
import com.example.saveodemo.repository.MainRepository

class MainViewModel : ViewModel() {

    private lateinit var featuredList: MutableLiveData<BaseResponse>
    private var myFeaturedInfo: MainRepository? = null
    fun init() {
        myFeaturedInfo = MainRepository().getInstance()
        featuredList = myFeaturedInfo!!.getFeaturedData()
    }

    fun getFeaturedList(): MutableLiveData<BaseResponse> {
        return featuredList
    }

}