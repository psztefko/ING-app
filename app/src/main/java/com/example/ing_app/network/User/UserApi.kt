package com.example.ing_app.network.User

import android.content.Context
import com.example.ing_app.R
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

class UserApi(private val context: Context){
    private val BASE_URL = "https://jsonplaceholder.typicode.com"

    // Build the Moshi object that Retrofit will be using, making sure to add the Kotlin adapter for
    // full Kotlin compatibility.
    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    // Creating own timeout limits
    private var okBuilder = OkHttpClient.Builder()
        .readTimeout(R.integer.read_timeout.toLong(), TimeUnit.MILLISECONDS)
        .connectTimeout(R.integer.connect_timeout.toLong(), TimeUnit.MILLISECONDS)
        .build()

    // Use the Retrofit builder to build a retrofit object using a Moshi converter
    // with our Moshi object.
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .baseUrl(BASE_URL)
        .build()

    fun getApiService(): UserService {
        return retrofit.create(UserService::class.java)
    }

}
