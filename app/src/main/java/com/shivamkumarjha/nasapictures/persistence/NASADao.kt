package com.shivamkumarjha.nasapictures.persistence

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.shivamkumarjha.nasapictures.model.NASA

@Dao
interface NASADao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addData(nasa: NASA)

    @Query("SELECT * FROM nasa ORDER BY date DESC")
    fun getData(): List<NASA>

    @Query("SELECT * FROM nasa ORDER BY date DESC")
    fun getImages(): LiveData<List<NASA>>

    @Query("UPDATE nasa SET isBookmarked = :isBookmarked WHERE url LIKE :url")
    fun updateBookmark(isBookmarked: Boolean, url: String)

    @Query("SELECT * FROM nasa WHERE isBookmarked =:isBookmarked  ORDER BY date DESC")
    fun getBookmarkedImages(isBookmarked: Boolean = true): LiveData<List<NASA>>
}