package com.example.saveodemo.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.saveodemo.model.BaseSearchResponse
import com.example.saveodemo.repository.DetailRepository

class DetailViewModel : ViewModel() {

    private lateinit var searchResult: LiveData<BaseSearchResponse>

    fun init(movieId: Int) {
        searchResult = DetailRepository().getInstance()!!.getSearchData(movieId)
    }

    fun getAllDetail(): LiveData<BaseSearchResponse> {
        return searchResult
    }


}