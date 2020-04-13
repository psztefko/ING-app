package com.example.ing_app.ui.posts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ing_app.repository.PostRepository
import kotlinx.coroutines.launch

class PostViewModel(private val postRepository: PostRepository) : ViewModel() {
    fun getPosts() {
        viewModelScope.launch {
            val apiResult = postRepository.getAllPosts()
        }
    }
}