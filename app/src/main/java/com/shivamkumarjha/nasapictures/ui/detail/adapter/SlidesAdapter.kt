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
import com.shivamkumarjha.nasapictures.databinding.ItemSlideBinding
import com.shivamkumarjha.nasapictures.model.NASA

class SlidesAdapter(private val slides: List<NASA>) : PagerAdapter() {


    override fun isViewFromObject(view: View, `object`: Any): Boolean =
        view == `object` as NestedScrollView

    override fun getCount(): Int = slides.size

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val binding =
            ItemSlideBinding.inflate(LayoutInflater.from(container.context), container, false)

        val imageView: AppCompatImageView = binding.detailImage
        val title: MaterialTextView = binding.detailTitle
        val explanation: MaterialTextView = binding.detailExplanation
        val copyright: MaterialTextView = binding.detailCopyright
        val date: MaterialTextView = binding.detailDate

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

        container.addView(binding.root)
        return binding.root
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as NestedScrollView)
    }
}