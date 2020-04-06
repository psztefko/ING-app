package com.example.ing_app.network

import retrofit2.Call
import retrofit2.http.GET

interface UsersApiService {
    @GET("/users")
    fun fetchAllUsers(): Call<List<User>>
}