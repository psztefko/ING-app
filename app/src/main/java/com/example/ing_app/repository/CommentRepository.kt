package com.example.ing_app.repository

import com.example.ing_app.network.Comment.CommentService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

class CommentRepository (private val commentService: CommentService){
    suspend fun getComments(){
        withContext(Dispatchers.IO){
            val request = commentService.getComments()
            Timber.d("onCommentsRecieved $request")
        }
    }
}