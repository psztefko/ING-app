package com.example.ing_app.network.Image

import android.content.Context
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class ImageApi (private val context: Context){
    private val BASE_URL = "http://jsonplaceholder.typicode.com"

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

    fun getApiService(): PhotoService {
        return retrofit.create(PhotoService::class.java)
    }
}