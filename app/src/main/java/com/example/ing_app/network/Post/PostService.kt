
package com.example.ing_app.network.Post

import com.example.ing_app.domain.Comment
import com.example.ing_app.domain.Post
import com.example.ing_app.domain.User
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PostService {
    // We can delete deferred and add suspend for function because
    // retrofit +2.6.0 supports async without deferred
    @GET("/posts")
    fun getPosts(): Deferred<List<Post>>
    @GET("/users/{userId}")
    fun getUser(@Path("userId") userId : Int): Deferred<User>
    @GET("/comments")
    fun getCommentsFromPost(@Query("postId") postId: Int): Deferred<List<Comment>>
    @GET("/users")
    fun getUsers(): Deferred<List<User>>
    @GET("/comments")
    fun getComments(): Deferred<List<Comment>>
}