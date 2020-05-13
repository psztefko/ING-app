package com.example.ing_app.repository

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.MutableLiveData
import com.example.ing_app.common.Result
import com.example.ing_app.common.exception.CancelledFetchDataException
import com.example.ing_app.common.exception.NetworkException
import com.example.ing_app.domain.Comment
import com.example.ing_app.domain.Post
import com.example.ing_app.domain.User
import com.example.ing_app.network.Post.PostService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber


class PostRepository (private val postService: PostService) {

    var errorScreen: MutableLiveData<Int> = MutableLiveData()
    

    suspend fun getPosts() : Result<List<Post>> {
        var result: Result<List<Post>> = Result.success(emptyList())

        errorScreen.value = View.GONE

        withContext(Dispatchers.IO) {
            try {
                val request = postService.getPosts()
                val response = request.await()
                Timber.d("onPostsReceived {$request}")

                request.let {
                    if (it.isCompleted) {
                        result = Result.success(response)
                    }
                    else if (it.isCancelled) {
                        result = Result.failure(CancelledFetchDataException())
                    }
                }
            } catch (ex: Throwable) {
                errorScreen.value = View.VISIBLE
                result = Result.failure(NetworkException())
                Timber.d("onPostReceived NetworkException")
            }
        }
        return result
    }
    suspend fun getUserFromPost(postId:Int): Result<User> {
        var result: Result<User> = Result.success(User())
        withContext(Dispatchers.IO) {
            try {
                val request = postService.getUser(postId)
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
                errorScreen.value = View.VISIBLE
                result = Result.failure(NetworkException())
                Timber.d("onUserReceived NetworkException")
            }
        }
        return result
    }
    suspend fun getCommentsFromPost(postId:Int): Result<List<Comment>> {
        var result: Result<List<Comment>> = Result.success(emptyList())
        withContext(Dispatchers.IO) {
            try{
                val request = postService.getCommentsFromPost(postId)
                val response = request.await()
                Timber.d("onCommentsFromPostReceived $request")

                request.let {
                    if (it.isCompleted) {
                        result = Result.success(response)
                    } else if (it.isCancelled) {
                        result = Result.failure(CancelledFetchDataException())
                    }
                }
            } catch (ex: Throwable) {
                errorScreen.value = View.VISIBLE
                result = Result.failure(NetworkException())
                Timber.d("onCommentReceived NetworkException")
            }
        }
        return result
    }
}