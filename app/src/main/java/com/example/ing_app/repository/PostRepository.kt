package com.example.ing_app.repository

import com.example.ing_app.network.Network
import com.example.ing_app.network.PostService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

class PostRepository(private val postService: PostService) {
    suspend fun getAllPosts() {
        withContext(Dispatchers.IO) {
            val request = Network.posts.getAllPosts()
            val response = request.await()
            Timber.d("onPostsReceived ${response}")
        }
    }
}