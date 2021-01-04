package com.example.saveodemo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.example.saveodemo.R
import com.example.saveodemo.adapter.MyDisplayAdapter.MyViewHolder
import com.example.saveodemo.model.Result
import com.example.saveodemo.utility.OnClickAdapter

class MyDisplayAdapter(var context: Context, var onClickAdapter: OnClickAdapter) :
    PagedListAdapter<Result?, MyViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_list, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val circularProgressDrawable = CircularProgressDrawable(context)
        circularProgressDrawable.strokeWidth = 5f
        circularProgressDrawable.centerRadius = 30f
        circularProgressDrawable.start()
        holder.movieName.text = getItem(position)!!.title
        if (getItem(position)!!.posterPath != null) Glide.with(context)
            .load("http://image.tmdb.org/t/p/original" + getItem(position)!!.posterPath)
            .placeholder(circularProgressDrawable).into(holder.imageView)
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        var imageView: ImageView
        var movieName: TextView
        override fun onClick(v: View) {
            onClickAdapter.clickedRecycler(getItem(adapterPosition)!!.id!!, v)
        }

        init {
            imageView = itemView.findViewById(R.id.thumbnail)
            movieName = itemView.findViewById(R.id.tv_display_data)
            imageView.setOnClickListener(this)
        }
    }

    companion object {
        private val DIFF_CALLBACK =
            object : DiffUtil.ItemCallback<Result?>() {
                override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
                    return oldItem.id === newItem.id
                }

                override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
                    return oldItem.id == newItem.id
                }
            }
    }
}