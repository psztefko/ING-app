package com.example.ing_app.ui.comments

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ing_app.repository.CommentRepository
import kotlinx.coroutines.launch

class CommentViewModel (private val commentRepository: CommentRepository): ViewModel() {
    init {
        getComments()
    }

    fun getComments(){
        viewModelScope.launch {
            val apiResult = commentRepository.getComments()
        }
    }
}