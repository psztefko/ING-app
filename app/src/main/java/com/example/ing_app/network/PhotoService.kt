package com.example.ing_app.network

import com.example.ing_app.domain.Photo
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PhotoService {
    @GET("/photos")
    fun getAlbums(): Deferred<List<Photo>>
    @GET("/photos")
    fun getPhotosFromAlbum(@Query("albumId") albumId: Int): Deferred<List<Photo>>
    @GET("/albums/{albumId}")
    fun getPhoto(@Path("photoId") photoId: Int): Deferred<Photo>
}