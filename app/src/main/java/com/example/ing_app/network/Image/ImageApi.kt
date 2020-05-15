package com.example.ing_app.network.Image

import android.content.Context
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import okhttp3.logging.HttpLoggingInterceptor

class ImageApi (private val context: Context){
    private val BASE_URL = "https://jsonplaceholder.typicode.com"

    // Build the Moshi object that Retrofit will be using, making sure to add the Kotlin adapter for
    // full Kotlin compatibility.
    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val logging = HttpLoggingInterceptor()

    val okBuilder = OkHttpClient.Builder().addInterceptor(logging).build()

    // Use the Retrofit builder to build a retrofit object using a Moshi converter
    // with our Moshi object.
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .baseUrl(BASE_URL)
        .client(okBuilder)
        .build()

    fun getApiService(): ImageService {
        logging.level = HttpLoggingInterceptor.Level.BODY
        return retrofit.create(ImageService::class.java)
    }
}