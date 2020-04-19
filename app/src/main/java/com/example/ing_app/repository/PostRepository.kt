package com.example.ing_app.repository

import com.example.ing_app.network.Post.PostService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

class PostRepository (private val postService: PostService) {
    suspend fun getPosts() {
        withContext(Dispatchers.IO) {
            val request = postService.getPosts()
            Timber.d("onPostsReceived $request")
        }
    }
}