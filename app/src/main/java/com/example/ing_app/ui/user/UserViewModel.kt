package com.example.ing_app.ui.user

import android.view.View
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ing_app.common.ResultType
import com.example.ing_app.common.Result
import com.example.ing_app.domain.User
import com.example.ing_app.repository.UserRepository
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.launch
import timber.log.Timber

class UserViewModel (private val userKey: Int = 0,
                     private val userRepository: UserRepository): ViewModel(){

    private val _user: MutableLiveData<User> = MutableLiveData()
    val user: LiveData<User>
        get() = _user

    private val _isErrorLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val isErrorLiveData: LiveData<Boolean>
        get() = _isErrorLiveData

    private val _navigateToSelectedPhotos = MutableLiveData<Int>()
    val navigateToSelectedPhotos: LiveData<Int>
        get() = _navigateToSelectedPhotos

    private val _navigateToPosts = MutableLiveData<Boolean?>()
    val navigateToPosts: LiveData<Boolean?>
        get() = _navigateToPosts

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val userVisibility: MutableLiveData<Int> = MutableLiveData()

    init {
        getUser()
    }

    private fun getUser() {
        //nie
        viewModelScope.launch {
            loadingVisibility.value = View.VISIBLE
            userVisibility.value = View.GONE
            val apiResult = userRepository.getUserFromPost(userKey)
            updateUser(apiResult)
        }
    }

    private fun updateUser(result: Result<User>) {
        if (isResultSuccess(result.resultType)) {
            Timber.d("onUpdateUserSuccess called")
            _user.postValue(result.data)
            loadingVisibility.value = View.GONE
            userVisibility.value = View.VISIBLE
        } else {
            onResultError()
        }
    }

    fun onUserPhotosClicked(id: Int) {
        _navigateToSelectedPhotos.value = id
    }

    fun displayPhotosCopmlete() {
        _navigateToSelectedPhotos.value = null
    }

    fun doneNavigating() {
        _navigateToPosts.value = null
    }

    fun onClose() {
        _navigateToPosts.value = true
    }

    private fun isResultSuccess(resultType: ResultType): Boolean {
        //tak
        return resultType == ResultType.SUCCESS
    }

    private fun onResultError() = _isErrorLiveData.postValue(true)
}