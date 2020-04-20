package com.example.ing_app.repository

import com.example.ing_app.network.Comment.CommentService
import com.example.ing_app.network.Post.PostService
import com.example.ing_app.network.User.UserService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

class PostRepository (private val postService: PostService,
                      private val userService: UserService,
                      private val commentService: CommentService) {
    suspend fun getPosts() {
        withContext(Dispatchers.IO) {
            val request = postService.getPosts()
            Timber.d("onPostsReceived $request")
        }
    }
    suspend fun getUserFromPost(postId:Int) {
        withContext(Dispatchers.IO) {
            val request = userService.getUser(postId)
            Timber.d("onUserFromPostReceived $request")
        }
    }
    suspend fun getCommentsFromPost(postId:Int) {
        withContext(Dispatchers.IO) {
            val request = commentService.getCommentsFromPost(postId);
            Timber.d("onCommentsFromPostReceived $request")
        }
    }
}