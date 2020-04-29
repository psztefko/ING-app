package com.example.ing_app.domain

data class Comment (
    val postId: Int = 0,
    val id: Int = 0,
    val name: String = "",
    val email: String = "",
    val body: String = ""
)