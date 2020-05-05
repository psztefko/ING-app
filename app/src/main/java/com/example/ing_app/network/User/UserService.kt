package com.example.ing_app.network.User

import com.example.ing_app.domain.User
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path

interface UserService {
    @GET("/users/{userId}")
    fun getUser(@Path("userId") userId : Int): Deferred<User>
}