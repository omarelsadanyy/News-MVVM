package com.example.news_app.model

import androidx.room.Dao
import com.google.gson.annotations.SerializedName

@Dao
open class BaseRespone(
    @field:SerializedName("status")
    val status: String? = null,

    @field:SerializedName("code")
    val code: String? = null,

    @field:SerializedName("message")
    val message: String? = null
)
