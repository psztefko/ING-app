package com.example.ing_app.network

import com.example.ing_app.domain.Album
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AlbumService {
    @GET("/albums")
    fun getAlbums(): Deferred<List<Album>>
    @GET("/albums")
    fun getAlbumsFromUser(@Query("userId") userId: Int): Deferred<List<Album>>
    @GET("/albums/{albumId}")
    fun getAlbum(@Path("albumId") albumId: Int): Deferred<Album>
}