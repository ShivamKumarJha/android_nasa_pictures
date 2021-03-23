package com.shivamkumarjha.nasapictures.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "nasa")
data class NASA(
    @SerializedName("copyright") val copyright: String,
    @SerializedName("date") val date: String,
    @SerializedName("explanation") val explanation: String,
    @SerializedName("hdurl") val hdurl: String,
    @SerializedName("media_type") val media_type: String,
    @SerializedName("service_version") val service_version: String,
    @SerializedName("title") val title: String,
    @PrimaryKey @SerializedName("url") val url: String
)