package com.example.ing_app.ui.images

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ing_app.domain.Photo
import com.example.ing_app.repository.ImageRepository
import com.example.ing_app.common.Result
import com.example.ing_app.common.ResultType
import com.example.ing_app.domain.Album
import kotlinx.coroutines.launch
import timber.log.Timber

class ImageViewModel (private val photoKey: Int = 0,
    private val imageRepository: ImageRepository): ViewModel(){

    private val _albums: MutableLiveData<List<Album>> = MutableLiveData()
    val albums: LiveData<List<Album>>
        get() = _albums

    private val _photos: MutableLiveData<List<Photo>> = MutableLiveData()
    val photos: LiveData<List<Photo>>
        get() = _photos

    private val _isErrorLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val isErrorLiveData: LiveData<Boolean>
        get() = _isErrorLiveData

    private val _navigateToFullImage = MutableLiveData<String>()
    val navigateToFullImage: LiveData<String>
        get() = _navigateToFullImage

    init {
        getAlbums()
    }

    fun getAlbums(){
        viewModelScope.launch {
            val apiResult = imageRepository.getAlbumsFromUser(photoKey)
            Timber.d("getAlbums ${apiResult}")
            getPhotos(apiResult)
        }
    }

    fun getPhotos(result: Result<List<Album>>) {
        viewModelScope.launch {
            if (isResultSuccess(result.resultType)) {
                result.data?.forEach {
                    val apiResult = imageRepository.getPhotosFromAlbum(it.albumId)
                    Timber.d("getPhotos ${apiResult}")
                    updatePhotos(apiResult)
                }
            } else {
                onResultError()
            }
        }
    }

    private fun updatePhotos(result: Result<List<Photo>>) {
        if (isResultSuccess(result.resultType)) {
            _photos.postValue(result.data)
        } else {
            onResultError()
        }
    }

    fun onImageFullImageClicked(photoUrl: String) {
        _navigateToFullImage.value = photoUrl
    }

    private fun isResultSuccess(resultType: ResultType): Boolean {
        return resultType == ResultType.SUCCESS
    }

    private fun onResultError() = _isErrorLiveData.postValue(true)
}