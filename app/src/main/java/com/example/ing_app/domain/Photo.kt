package com.example.ing_app.domain

import com.squareup.moshi.Json

data class Photo (
    val albumId: Int,
    val photoId: Int,
    val title: String,
    val url: String,
    @Json(name = "img_src") val thumbnailUrl: String
)