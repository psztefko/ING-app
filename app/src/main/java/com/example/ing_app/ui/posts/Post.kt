package com.example.ing_app.ui.posts

data class Post (
    val id: Int = 0,
    val userId: Int = 0,
    val userName: String = "",
    val title: String = "",
    val body: String = "",
    val commentsAmount: Int = -1
)