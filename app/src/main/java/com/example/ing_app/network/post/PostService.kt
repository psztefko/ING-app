package com.example.ing_app.network.post

import com.example.ing_app.domain.Post
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface PostService {
    @GET("/posts")
    fun getPosts(): Deferred<List<Post>>
}