package com.example.saveodemo.activity

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.saveodemo.R
import com.example.saveodemo.model.BaseSearchResponse
import com.example.saveodemo.viewModel.DetailViewModel

class MainActivity : AppCompatActivity() {

    private var movieId: Int = 0
    private var detailViewModel: DetailViewModel? = null
    private lateinit var synipsis: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        movieId = intent.getIntExtra("movieId", 0)
        synipsis = findViewById(R.id.movieDetail)

        detailViewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        detailViewModel!!.init(movieId)
        detailViewModel!!.getAllDetail().observe(this, this::updateDetail)


    }


    fun updateDetail(baseSearchResponse: BaseSearchResponse) {
        synipsis.text = baseSearchResponse.overview
    }

}
