package com.example.saveodemo.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.saveodemo.R

class MainActivity : AppCompatActivity() {

    private var movieId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        movieId = intent.getIntExtra("movieId", 0)


    }
}