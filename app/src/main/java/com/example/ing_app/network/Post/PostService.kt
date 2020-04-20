
package com.example.ing_app.network.Post

import com.example.ing_app.domain.Post
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path

interface PostService {
    // We can delete deferred and add suspend for function because
    // retrofit +2.6.0 supports async without deferred
    @GET("/posts")
    fun getPosts(): Deferred<List<Post>>
    @GET("/posts/{postId}")
    fun getPost(@Path("postId") postId: Int): Deferred<Post>
}