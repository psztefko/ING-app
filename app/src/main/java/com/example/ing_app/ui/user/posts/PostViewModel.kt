package com.example.ing_app.ui.user.posts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ing_app.repository.PostRepository

class PostViewModel(private val postRepository: PostRepository) : ViewModel() {
    init {
        getPosts()
    }

    fun getPosts() {
        viewModelScope.launch {
            val apiResult = postRepository.getAllPosts()
        }
    }
}