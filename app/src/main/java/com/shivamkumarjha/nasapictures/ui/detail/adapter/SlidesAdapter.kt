package com.shivamkumarjha.nasapictures.ui.detail.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.shivamkumarjha.nasapictures.R
import com.shivamkumarjha.nasapictures.model.NASA

class SlidesAdapter(private val slides: ArrayList<NASA>) : PagerAdapter() {

    private lateinit var layoutInflater: LayoutInflater

    override fun isViewFromObject(view: View, `object`: Any): Boolean =
        view == `object` as LinearLayout

    override fun getCount(): Int = slides.size

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        layoutInflater = LayoutInflater.from(container.context)
        val view: View = layoutInflater.inflate(R.layout.item_slide, container, false)
        val imageView: ImageView = view.findViewById(R.id.slides_image_view)
        //Loader
        val circularProgressDrawable = CircularProgressDrawable(imageView.context)
        circularProgressDrawable.strokeWidth = 5f
        circularProgressDrawable.centerRadius = 30f
        circularProgressDrawable.start()
        //Image
        Glide.with(imageView.context)
            .load(slides[position].url)
            .placeholder(circularProgressDrawable)
            .apply(RequestOptions.centerInsideTransform())
            .into(imageView)
        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as LinearLayout)
    }
}