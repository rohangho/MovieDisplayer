package com.example.saveodemo.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.saveodemo.datasoursce.PlayingDataFactory
import com.example.saveodemo.model.BaseResponse
import com.example.saveodemo.model.Result
import com.example.saveodemo.repository.MainRepository

class MainViewModel : ViewModel() {

    private lateinit var featuredList: MutableLiveData<BaseResponse>
    private lateinit var allGifs: LiveData<PagedList<Result?>>

    private var myFeaturedInfo: MainRepository? = null
    fun init() {


        val playingDataFactory = PlayingDataFactory()
        val pagedListConfig = PagedList.Config.Builder()
            .setEnablePlaceholders(false).setPageSize(20)
            .build()

        allGifs = LivePagedListBuilder(playingDataFactory, pagedListConfig)
            .build()

        myFeaturedInfo = MainRepository().getInstance()
        featuredList = myFeaturedInfo!!.getFeaturedData()

    }

    fun getAllPlayingMovies(): LiveData<PagedList<Result?>> {
        return allGifs
    }

    fun getFeaturedList(): MutableLiveData<BaseResponse> {
        return featuredList
    }

}