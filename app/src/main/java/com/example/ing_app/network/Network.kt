package com.example.ing_app.network

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private const val BASE_URL = "http://jsonplaceholder.typicode.com"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

object Network {
    val posts: PostService by lazy { retrofit.create(PostService::class.java) }
    val comments: CommentService by lazy { retrofit.create(CommentService::class.java) }
    val photos: PhotoService by lazy { retrofit.create(PhotoService::class.java) }
    val albums: AlbumService by lazy { retrofit.create(AlbumService::class.java) }
    val users: UserService by lazy { retrofit.create(UserService::class.java) }
}