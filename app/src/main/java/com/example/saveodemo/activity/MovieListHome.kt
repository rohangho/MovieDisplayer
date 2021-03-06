package com.example.saveodemo.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
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
        displayViewPager.offscreenPageLimit = 3
        displayViewPager.setPadding(100, 0, 100, 0)
        displayViewPager.pageMargin = 60
        displayRecyclerView.layoutManager = GridLayoutManager(this, 3)
        myDisplayAdapter = MyDisplayAdapter(this, object : OnClickAdapter {
            override fun clickedRecycler(id: Int, v: View) {
                val activityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(
                    this@MovieListHome,
                    v,
                    "imageMain"
                )
                startActivity(
                    Intent(
                        applicationContext,
                        MainActivity::class.java
                    ).putExtra("movieId", id), activityOptionsCompat.toBundle()
                )
            }

        })
        displayRecyclerView.adapter = myDisplayAdapter
        homeViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        homeViewModel!!.init()
        homeViewModel!!.getAllPlayingMovies().observe(this, {
            myDisplayAdapter.submitList(it)
        })
        homeViewModel!!.getFeaturedMovie().observe(this, this::updateUi)

    }

    /**
     * This method update the UI of the coming soom movie
     */

    private fun updateUi(detail: BaseResponse) {
        var imagList: ArrayList<String> = ArrayList()
        for (i in detail.results!!.indices) {
            imagList.add("http://image.tmdb.org/t/p/original/" + detail.results!![i].posterPath)
        }

        displayViewPager.adapter = SlidingImage_Adapter(this, imagList)
    }
}