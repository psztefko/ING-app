package com.example.ing_app.network.Comment

import android.content.Context
import com.example.ing_app.network.Post.PostService
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class CommentApi (private val context: Context){
    private val BASE_URL = "https://jsonplaceholder.typicode.com"

    // Build the Moshi object that Retrofit will be using, making sure to add the Kotlin adapter for
    // full Kotlin compatibility.
    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    // Use the Retrofit builder to build a retrofit object using a Moshi converter
    // with our Moshi object.
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .baseUrl(BASE_URL)
        .build()

    fun getApiService(): CommentService {
        return retrofit.create(CommentService::class.java)
    }
}