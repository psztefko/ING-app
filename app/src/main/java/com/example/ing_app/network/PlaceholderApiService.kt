package com.example.ing_app.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
//

private const val BASE_URL = "https://jsonplaceholder.typicode.com"

val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(MoshiConverterFactory.create())
    .build()

data class PostProperties(@field:Json(name = "userId") val userId: Int,
                          @field:Json(name = "id") val id: Int,
                          @field:Json(name = "title") val title: String,
                          @field:Json(name = "body") val body: String)

interface PlaceholderApiService{
    @GET("posts")
    fun getProperties():
            Call<String>
}

object PlaceholderApi{
    val retrofitService : PlaceholderApiService by lazy{
        retrofit.create(PlaceholderApiService::class.java)
    }
}



/*
@JsonClass(generateAdapter = true)
data class Post (
    //@Json(name = “userId”) annotation defines the JSON key name for serialisation
    //and the property to set the value on with deserialization.
    @Json(name = "userId") val userId: Int = -1,
    val id: Int,
    val title: String,
    val body: String
)

val moshi: Moshi = Moshi.Builder().build()
val adapter: JsonAdapter<Post> = moshi.adapter(Post::class.java)
val post = adapter.fromJson(postsJson))*/