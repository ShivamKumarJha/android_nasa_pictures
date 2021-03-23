package com.shivamkumarjha.nasapictures.ui.detail.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.shivamkumarjha.nasapictures.R
import com.shivamkumarjha.nasapictures.model.NASA

class SlidesAdapter(private val slides: ArrayList<NASA>) : PagerAdapter() {

    private lateinit var layoutInflater: LayoutInflater

    override fun isViewFromObject(view: View, `object`: Any): Boolean =
        view == `object` as ConstraintLayout

    override fun getCount(): Int = slides.size

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        layoutInflater = LayoutInflater.from(container.context)
        val view: View = layoutInflater.inflate(R.layout.item_slide, container, false)

        val imageView: ImageView = view.findViewById(R.id.slides_image_view)
        val title: TextView = view.findViewById(R.id.title_tv)
        val explanation: TextView = view.findViewById(R.id.explanation_tv)
        val copyright: TextView = view.findViewById(R.id.copyright_tv)
        val date: TextView = view.findViewById(R.id.date_tv)

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
        //TextFields
        title.text = slides[position].title
        explanation.text = slides[position].explanation
        copyright.text = slides[position].copyright
        date.text = slides[position].date
        copyright.isVisible = !slides[position].copyright.isNullOrEmpty()

        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as ConstraintLayout)
    }
}