package com.example.ing_app.repository

import com.example.ing_app.common.exception.CancelledFetchDataException
import com.example.ing_app.common.exception.NetworkException
import com.example.ing_app.common.Result
import com.example.ing_app.domain.Comment
import com.example.ing_app.network.Comment.CommentService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

class CommentRepository (private val commentService: CommentService){
    suspend fun getCommentsFromPost(postId:Int): Result<List<Comment>> {
        var result: Result<List<Comment>> = Result.success(emptyList())
        withContext(Dispatchers.IO) {
            try{
                val request = commentService.getCommentsFromPost(postId)
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
}