package com.example.saveodemo.adapter

import android.content.Context
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.example.saveodemo.R
import java.util.*

class SlidingImage_Adapter(private val context: Context, private val IMAGES: ArrayList<String>) :
        PagerAdapter() {
    private val inflater: LayoutInflater = LayoutInflater.from(context)
    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun getCount(): Int {
        return IMAGES.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun restoreState(state: Parcelable?, loader: ClassLoader?) {}
    override fun saveState(): Parcelable? {
        return null
    }

    override fun instantiateItem(view: ViewGroup, position: Int): Any {
        val imageLayout = inflater.inflate(R.layout.inside_horizontal_slider, view, false)!!
        val imageView = imageLayout
                .findViewById<View>(R.id.image) as ImageView
        val circularProgressDrawable = CircularProgressDrawable(context)
        circularProgressDrawable.strokeWidth = 5f
        circularProgressDrawable.centerRadius = 30f
        circularProgressDrawable.start()
        Glide.with(context).load(IMAGES[position]).placeholder(circularProgressDrawable)
                .into(imageView)
        view.addView(imageLayout, 0)
        return imageLayout
    }

}