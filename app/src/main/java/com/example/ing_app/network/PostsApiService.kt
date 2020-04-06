package com.example.ing_app.network

import retrofit2.Call
import retrofit2.http.GET

interface PostsApiService {
    @GET("/posts")
    fun fetchAllPosts(): Call<List<Post>>
}