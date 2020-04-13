package com.example.ing_app.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private const val BASE_URL = "http://jsonplaceholder.typicode.com"

/**
 * Build the Moshi object that Retrofit will be using, making sure to add the Kotlin adapter for
 * full Kotlin compatibility.
*/
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

/**
 * Use the Retrofit builder to build a retrofit object using a Moshi converter
 * with our Moshi object.
*/
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