package com.example.ing_app.ui.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ing_app.domain.User
import com.example.ing_app.repository.UserRepository
import kotlinx.coroutines.launch
import timber.log.Timber

class UserViewModel (private val userKey: Int = 0,
                     private val userRepository: UserRepository): ViewModel(){
    private val _user: MutableLiveData<User> = MutableLiveData()
    val user: LiveData<User>
        get() = _user

    private fun getUser() {
        viewModelScope.launch {
            Timber.d("getUser")
            val apiResult = userRepository.getUserFromPost(userKey)
        }
    }
}