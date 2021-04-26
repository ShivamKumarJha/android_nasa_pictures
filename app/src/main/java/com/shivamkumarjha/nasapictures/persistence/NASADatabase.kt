package com.shivamkumarjha.nasapictures.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import com.shivamkumarjha.nasapictures.model.NASA

@Database(
    entities = [
        NASA::class
    ],
    version = 2
)

abstract class NASADatabase : RoomDatabase() {
    abstract fun nasaDao(): NASADao
}