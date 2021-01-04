package com.example.saveodemo.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.example.saveodemo.R
import com.example.saveodemo.adapter.MyDisplayAdapter
import com.example.saveodemo.adapter.SlidingImage_Adapter
import com.example.saveodemo.model.BaseResponse
import com.example.saveodemo.utility.OnClickAdapter
import com.example.saveodemo.viewModel.MainViewModel


class MovieListHome : AppCompatActivity() {

    private var homeViewModel: MainViewModel? = null
    private lateinit var displayViewPager: ViewPager
    private lateinit var displayRecyclerView: RecyclerView
    private lateinit var myDisplayAdapter: MyDisplayAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list_home)
        displayRecyclerView = findViewById(R.id.playingMovieList)
        displayViewPager = findViewById(R.id.hori_slider)
        displayViewPager.clipToPadding = false
        displayViewPager.setPadding(80, 0, 80, 0)
        displayViewPager.pageMargin = 60
        displayRecyclerView.layoutManager = GridLayoutManager(this, 3)
        myDisplayAdapter = MyDisplayAdapter(this, object : OnClickAdapter {
            override fun clickedRecycler(id: Int) {
                startActivity(
                    Intent(
                        applicationContext,
                        MainActivity::class.java
                    ).putExtra("movieId", id)
                )
            }

        })
        displayRecyclerView.adapter = myDisplayAdapter
        homeViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        homeViewModel!!.init()
        homeViewModel!!.getAllPlayingMovies().observe(this, {
            myDisplayAdapter.submitList(it)
        })
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