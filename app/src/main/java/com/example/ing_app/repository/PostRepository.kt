package com.example.ing_app.repository

import com.example.ing_app.common.Result
import com.example.ing_app.common.exception.CancelledFetchDataException
import com.example.ing_app.common.exception.NetworkException
import com.example.ing_app.domain.Post
import com.example.ing_app.network.Comment.CommentService
import com.example.ing_app.network.Post.PostService
import com.example.ing_app.network.User.UserService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

class PostRepository (private val postService: PostService,
                      private val userService: UserService,
                      private val commentService: CommentService) {
    suspend fun getPosts() : Result<List<Post>> {
        var result: Result<List<Post>> = Result.success(emptyList())

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
                result = Result.failure(NetworkException())
                Timber.d("onPostReceived NetworkException")
            }
        }
        return result
    }
    suspend fun getUserFromPost(postId:Int) {
        withContext(Dispatchers.IO) {
            val request = userService.getUser(postId)
            Timber.d("onUserFromPostReceived $request")
        }
    }
    suspend fun getCommentsFromPost(postId:Int) {
        withContext(Dispatchers.IO) {
            val request = commentService.getCommentsFromPost(postId)
            Timber.d("onCommentsFromPostReceived $request")
        }
    }
}