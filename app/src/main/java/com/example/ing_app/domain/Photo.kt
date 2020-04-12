package com.example.ing_app.domain

data class Photo (
    val albumId: Int,
    val photoId: Int,
    val title: String,
    val url: String,
    val thumbnailUrl: String
)