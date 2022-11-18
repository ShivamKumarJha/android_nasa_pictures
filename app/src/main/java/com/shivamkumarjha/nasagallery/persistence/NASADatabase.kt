package com.shivamkumarjha.nasagallery.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import com.shivamkumarjha.nasagallery.model.NASA

@Database(
    entities = [
        NASA::class
    ],
    version = 2
)

abstract class NASADatabase : RoomDatabase() {
    abstract fun nasaDao(): NASADao
}