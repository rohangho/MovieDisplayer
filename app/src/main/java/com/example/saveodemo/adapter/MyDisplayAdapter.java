package com.example.saveodemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import com.bumptech.glide.Glide;
import com.example.saveodemo.R;
import com.example.saveodemo.model.Result;

public class MyDisplayAdapter extends PagedListAdapter<Result, MyDisplayAdapter.MyViewHolder> {


    private static final DiffUtil.ItemCallback<Result> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Result>() {
                @Override
                public boolean areItemsTheSame(Result oldItem, Result newItem) {
                    return oldItem.getId() == newItem.getId();
                }

                @Override
                public boolean areContentsTheSame(Result oldItem, Result newItem) {
                    return oldItem.getId().equals(newItem.getId());
                }
            };
    Context context;
    //  OnClickAdapter onClickAdapter;

    public MyDisplayAdapter(Context context) {
        super(DIFF_CALLBACK);
        this.context = context;
        // this.onClickAdapter = onClickAdapter;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        CircularProgressDrawable circularProgressDrawable = new CircularProgressDrawable(context);
        circularProgressDrawable.setStrokeWidth(5f);
        circularProgressDrawable.setCenterRadius(30f);
        circularProgressDrawable.start();
        Glide.with(context).load("http://image.tmdb.org/t/p/original" + getItem(position).getPosterPath()).placeholder(circularProgressDrawable).into(holder.imageView);
    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView imageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.thumbnail);
            imageView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            //  onClickAdapter.clickedRecycler(getItem(getAdapterPosition()).getImages().getOriginal().getUrl());
        }
    }
}
