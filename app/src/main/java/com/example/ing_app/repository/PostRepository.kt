package com.example.ing_app.repository


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
                result = Result.failure(NetworkException())
                Timber.d("onUserReceived NetworkException")
            }
        }
        return result
    }
    suspend fun getUsers(): Result<List<User>> {
        var result: Result<List<User>> = Result.success(emptyList())
        withContext(Dispatchers.IO) {
            try {
                val request = postService.getUsers()
                val response = request.await()
                Timber.d("onUsersReceived {$request}")

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
                result = Result.failure(NetworkException())
                Timber.d("onCommentReceived NetworkException")
            }
        }
        return result
    }
    suspend fun getComments(): Result<List<Comment>> {
        var result: Result<List<Comment>> = Result.success(emptyList())
        withContext(Dispatchers.IO) {
            try{
                val request = postService.getComments()
                val response = request.await()
                Timber.d("onCommentsReceived $request")

                request.let {
                    if (it.isCompleted) {
                        result = Result.success(response)
                    } else if (it.isCancelled) {
                        result = Result.failure(CancelledFetchDataException())
                    }
                }
            } catch (ex: Throwable) {
                result = Result.failure(NetworkException())
                Timber.d("onCommentReceived NetworkException")
            }
        }
        return result
    }
}