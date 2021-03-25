package com.shivamkumarjha.nasapictures.ui.detail.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.view.isVisible
import androidx.core.widget.NestedScrollView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.textview.MaterialTextView
import com.shivamkumarjha.nasapictures.R
import com.shivamkumarjha.nasapictures.model.NASA

class SlidesAdapter(private val slides: List<NASA>) : PagerAdapter() {

    private lateinit var layoutInflater: LayoutInflater

    override fun isViewFromObject(view: View, `object`: Any): Boolean =
        view == `object` as NestedScrollView

    override fun getCount(): Int = slides.size

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        layoutInflater = LayoutInflater.from(container.context)
        val view: View = layoutInflater.inflate(R.layout.item_slide, container, false)

        val imageView: AppCompatImageView = view.findViewById(R.id.detail_image)
        val title: MaterialTextView = view.findViewById(R.id.detail_title)
        val explanation: MaterialTextView = view.findViewById(R.id.detail_explanation)
        val copyright: MaterialTextView = view.findViewById(R.id.detail_copyright)
        val date: MaterialTextView = view.findViewById(R.id.detail_date)

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
        container.removeView(`object` as NestedScrollView)
    }
}