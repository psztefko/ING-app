package com.example.ing_app.repository

import com.example.ing_app.common.Result
import com.example.ing_app.common.exception.CancelledFetchDataException
import com.example.ing_app.common.exception.NetworkException
import com.example.ing_app.domain.User
import com.example.ing_app.network.user.UserService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

class UserRepository(private val userService: UserService) {
    suspend fun getUserFromPost(postId: Int): Result<User> {
        var result: Result<User> = Result.success(User())
        withContext(Dispatchers.IO) {
            try {
                val request = userService.getUser(postId)
                val response = request.await()
                Timber.d("onUserReceived {$request}")

                request.let {
                    if (it.isCompleted) {
                        result = Result.success(response)
                    } else if (it.isCancelled) {
                        result = Result.failure(CancelledFetchDataException())
                    }
                }
            } catch (ex: Throwable) {
                result = Result.failure(NetworkException())
                Timber.d("onUserReceived NetworkException")
            }
        }
        return result
    }
}