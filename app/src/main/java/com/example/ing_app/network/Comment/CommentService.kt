package com.example.ing_app.network.Comment

import com.example.ing_app.domain.Comment
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface CommentService {
    @GET("/comments")
    fun getCommentsFromPost(@Query("postId") postId: Int): Deferred<List<Comment>>
}