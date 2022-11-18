package com.shivamkumarjha.nasagallery.persistence

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.shivamkumarjha.nasagallery.model.NASA

@Dao
interface NASADao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addImage(nasa: NASA)

    @Query("SELECT * FROM nasa ORDER BY date DESC")
    fun getImages(): LiveData<List<NASA>>
}