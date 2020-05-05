package com.example.ing_app.ui.images

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ing_app.domain.Photo
import com.example.ing_app.repository.ImageRepository
import com.example.ing_app.common.Result
import com.example.ing_app.common.ResultType
import kotlinx.coroutines.launch

class ImageViewModel (private val imageRepository: ImageRepository): ViewModel(){

    private val _photos: MutableLiveData<List<Photo>> = MutableLiveData()
    val photos: LiveData<List<Photo>>
        get() = _photos

    private val _isErrorLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val isErrorLiveData: LiveData<Boolean>
        get() = _isErrorLiveData

    init {
        getImages()
    }

    fun getImages(){
        viewModelScope.launch {
            val apiResult = imageRepository.getImages()
        }
    }

    private fun updatePhotos(result: Result<List<Photo>>) {
        if (isResultSuccess(result.resultType)) {
            _photos.postValue(result.data)
        } else {
            onResultError()
        }

    }

    private fun isResultSuccess(resultType: ResultType): Boolean {
        return resultType == ResultType.SUCCESS
    }

    private fun onResultError() = _isErrorLiveData.postValue(true)
}