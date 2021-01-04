package com.example.saveodemo.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.example.saveodemo.R
import com.example.saveodemo.adapter.SlidingImage_Adapter
import com.example.saveodemo.model.BaseResponse
import com.example.saveodemo.viewModel.MainViewModel


class MovieListHome : AppCompatActivity() {

    private var homeViewModel: MainViewModel? = null
    private lateinit var displayViewPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list_home)

        displayViewPager = findViewById(R.id.hori_slider)
        homeViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        homeViewModel!!.init()
        homeViewModel!!.getFeaturedList().observe(this, this::updateUi)

    }

    private fun updateUi(detail: BaseResponse) {
        var imagList: ArrayList<String> = ArrayList()
        for (i in detail.results!!.indices) {
            imagList.add("http://image.tmdb.org/t/p/original/" + detail.results!![i].posterPath)
        }

        displayViewPager.adapter = SlidingImage_Adapter(this, imagList)
    }
}