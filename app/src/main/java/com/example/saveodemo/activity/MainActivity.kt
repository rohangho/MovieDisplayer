package com.example.saveodemo.activity

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.example.saveodemo.R
import com.example.saveodemo.model.BaseSearchResponse
import com.example.saveodemo.viewModel.DetailViewModel

class MainActivity : AppCompatActivity() {

    private var movieId: Int = 0
    private var detailViewModel: DetailViewModel? = null
    private lateinit var synipsis: TextView
    private lateinit var posterImage: ImageView
    private lateinit var circularProgressDrawable: CircularProgressDrawable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        movieId = intent.getIntExtra("movieId", 0)
        synipsis = findViewById(R.id.movieDetail)
        posterImage = findViewById(R.id.poster)
        circularProgressDrawable = CircularProgressDrawable(this)
        circularProgressDrawable.strokeWidth = 5f
        circularProgressDrawable.centerRadius = 30f
        circularProgressDrawable.start()

        detailViewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        detailViewModel!!.init(movieId)
        detailViewModel!!.getAllDetail().observe(this, this::updateDetail)


    }


    fun updateDetail(baseSearchResponse: BaseSearchResponse) {
        synipsis.text = baseSearchResponse.overview
        Glide.with(this)
            .load("http://image.tmdb.org/t/p/original" + baseSearchResponse.posterPath)
            .placeholder(circularProgressDrawable).into(posterImage)

    }

}
