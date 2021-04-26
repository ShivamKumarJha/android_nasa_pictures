package com.shivamkumarjha.nasapictures.ui.main.adapter

interface NASAClickListener {
    fun onCardClick(position: Int)
    fun updateBookmark(isBookmarked: Boolean, url: String)
}