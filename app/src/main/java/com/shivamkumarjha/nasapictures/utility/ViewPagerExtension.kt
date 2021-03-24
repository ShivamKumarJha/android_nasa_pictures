package com.shivamkumarjha.nasapictures.utility

import androidx.viewpager.widget.ViewPager

fun ViewPager.onPageSelected(onPageSelected: (position: Int) -> Unit = {}) =
    addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int
        ) {
        }

        override fun onPageScrollStateChanged(state: Int) {}

        override fun onPageSelected(position: Int) {
            onPageSelected(position)
        }
    })