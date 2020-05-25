package com.example.ing_app.repository

import com.example.ing_app.common.Result
import com.example.ing_app.common.exception.CancelledFetchDataException
import com.example.ing_app.common.exception.NetworkException
import com.example.ing_app.domain.Photo
import com.example.ing_app.domain.Album
import com.example.ing_app.network.image.ImageService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

class ImageRepository (private val imageService: ImageService){

    suspend fun getAlbumsFromUser(userId:Int): Result<List<Album>> {
        var result: Result<List<Album>> = Result.success(emptyList())
        withContext(Dispatchers.IO) {
            try{
                val request = imageService.getAlbumsFromUser(userId)
                val response = request.await()
                Timber.d("onAlbumFromUserReceived $request")

                request.let {
                    if (it.isCompleted) {
                        result = Result.success(response)
                    } else if (it.isCancelled) {
                        result = Result.failure(CancelledFetchDataException())
                    }
                }
            } catch (ex: Throwable) {
                result = Result.failure(NetworkException())
                Timber.d("onAlbumReceived NetworkException")
            }
        }
        return result
    }
    suspend fun getPhotosFromAlbum(albumId:Int): Result<List<Photo>> {
        var result: Result<List<Photo>> = Result.success(emptyList())
        withContext(Dispatchers.IO) {
            try{
                val request = imageService.getPhotosFromAlbum(albumId)
                val response = request.await()
                Timber.d("onPhotosFromAlbumReceived $request")

                request.let {
                    if (it.isCompleted) {
                        result = Result.success(response)
                    } else if (it.isCancelled) {
                        result = Result.failure(CancelledFetchDataException())
                    }
                }
            } catch (ex: Throwable) {
                result = Result.failure(NetworkException())
                Timber.d("onPhotosReceived NetworkException")
            }
        }
        return result
    }
}