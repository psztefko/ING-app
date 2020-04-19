package com.example.ing_app.repository

import com.example.ing_app.network.User.UserService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

class UserRepository (private val userService: UserService){
    suspend fun getUsers(){
        withContext(Dispatchers.IO){
            val request = userService.getUsers()
            val response = request
            Timber.d("onUsersRecieved ${response}")
        }
    }
}