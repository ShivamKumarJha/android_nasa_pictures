package com.shivamkumarjha.nasapictures.persistence

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
}