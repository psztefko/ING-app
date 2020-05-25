package com.example.ing_app.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.ing_app.ui.posts.Post

@Entity
data class DatabasePost (
    @PrimaryKey
    val id: Int,
    val userId: Int,
    val userName: String,
    val title: String,
    val body: String,
    val commentsAmount: Int
)

fun List<DatabasePost>.asDomainModel(): List<Post> {
    return map {
        Post(
            id = it.id,
            userId = it.userId,
            userName = it.userName,
            title = it.title,
            body = it.body,
            commentsAmount = it.commentsAmount
        )
    }
}