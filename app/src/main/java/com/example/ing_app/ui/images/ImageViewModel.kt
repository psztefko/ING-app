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

class ImageViewModel (private val userKey: Int = 0,
                      private val imageRepository: ImageRepository): ViewModel(){

    private var _photosList = mutableListOf<Photo>()
    val photosList: List<Photo>
        get() = _photosList


    private val _photos: MutableLiveData<List<Photo>> = MutableLiveData()
    val photos: LiveData<List<Photo>>
        get() = _photos

    private val _isErrorLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val isErrorLiveData: LiveData<Boolean>
        get() = _isErrorLiveData

    private val _navigateToFullImage = MutableLiveData<String>()
    val navigateToFullImage: LiveData<String>
        get() = _navigateToFullImage

    private val _navigateToUser = MutableLiveData<Boolean?>()
    val navigateToUser: LiveData<Boolean?>
        get() = _navigateToUser

    init {
        getAlbums()
    }

    // We can also take all albums and filter it but it is fine that way

    fun getAlbums(){
        viewModelScope.launch {
            Timber.d("getAlbums userKey: $userKey")
            val apiResult = imageRepository.getAlbumsFromUser(userKey)
            Timber.d("getAlbums ${apiResult}")
            getPhotos(apiResult)
        }
    }

    fun getPhotos(result: Result<List<Album>>) {
        viewModelScope.launch {
            if (isResultSuccess(result.resultType)) {
                result.data?.forEach {
                    val apiResult = imageRepository.getPhotosFromAlbum(it.id)
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
            // This probably is slower than previous solution but shows all photos
            result.data?.forEach { photo -> _photosList.add(photo) }
            Timber.d("last element of photosList: ${photosList.last()}")
            _photos.postValue(photosList)
        } else {
            onResultError()
        }
    }

    fun onImageFullImageClicked(photoUrl: String) {
        _navigateToFullImage.value = photoUrl
    }

    fun doneNavigating() {
        _navigateToUser.value = null
    }

    fun onClose() {
        _navigateToUser.value = true
    }

    private fun isResultSuccess(resultType: ResultType): Boolean {
        return resultType == ResultType.SUCCESS
    }

    private fun onResultError() = _isErrorLiveData.postValue(true)
}