package com.example.saveodemo.datasoursce

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.saveodemo.model.Result

class PlayingDataFactory : DataSource.Factory<Int, Result>() {
    val playingMovieDataScousce: MutableLiveData<PlayingMoviesDataSoursce> = MutableLiveData()


    override fun create(): DataSource<Int, Result> {
        val playDataString = PlayingMoviesDataSoursce()
        playingMovieDataScousce.postValue(playDataString)
        return playDataString
    }
}