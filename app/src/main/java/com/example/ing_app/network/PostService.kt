package com.example.ing_app.network

import com.example.ing_app.domain.Post
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path

interface PostService {
    @GET("/posts")
    fun getAllPosts(): Deferred<List<Post>>
    @GET("/posts/{postId}")
    fun getPost(@Path("postId") postId: Int): Deferred<Post>
}