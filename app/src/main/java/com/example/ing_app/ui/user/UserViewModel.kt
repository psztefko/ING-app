package com.example.ing_app.ui.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ing_app.repository.UserRepository
import kotlinx.coroutines.launch

class UserViewModel (private val userRepository: UserRepository): ViewModel(){
    init{
        getUsers()
    }

    fun getUsers(){
        viewModelScope.launch {
            val apiResult = userRepository.getUsers()
        }
    }
}