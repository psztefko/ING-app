package com.example.ing_app.domain

data class Comment (
    val postId: Int,
    val commentId: Int,
    val name: String,
    val email: String,
    val body: String
)