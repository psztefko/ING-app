package com.example.ing_app.network.Image

import com.example.ing_app.domain.Album
import com.example.ing_app.domain.Photo
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PhotoService {
    @GET("/photos")
    fun getPhotosFromAlbum(@Query("albumId") albumId: Int): Deferred<List<Photo>>
    @GET("/albums")
    fun getAlbumsFromUser(@Query("userId") userId: Int): Deferred<List<Album>>
}