package com.example.ing_app.repository

import com.example.ing_app.network.Image.PhotoService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

class ImageRepository (private val photoService: PhotoService){
    suspend fun getImages(){
        withContext(Dispatchers.IO){
            val request = photoService.getAlbums()
            Timber.d("onPhotoRecieved $request")
        }
    }
}