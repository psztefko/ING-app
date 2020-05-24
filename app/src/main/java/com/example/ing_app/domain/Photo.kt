package com.example.ing_app.domain

import com.squareup.moshi.Json

data class Photo (
    val albumId: Int,
    val id: Int,
    val title: String,
    val url: String,
    val thumbnailUrl: String
)