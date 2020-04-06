package com.example.ing_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.d
import android.widget.ListView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ing_app.network.*
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private const val BASE_URL = "https://jsonplaceholder.typicode.com"

class MainActivity : AppCompatActivity() {

    private lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        val postApi = retrofit.create(PostsApiService::class.java)

        postApi.fetchAllPosts().enqueue(object : Callback<List<Post>>{
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                showPostData(response.body()!!)
            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                d("tag", "onFailure")
            }
        })

        val userApi = retrofit.create(UsersApiService::class.java)

        userApi.fetchAllUsers().enqueue(object : Callback<List<User>>{
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {

            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })

    }
    private fun showUserData(users: List<User>){
        recyclerView.apply{
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = UsersAdapter(users)
        }
    }

    private fun showPostData(posts: List<Post>){
        recyclerView.apply{
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = PostsAdapter(posts)
        }
    }
}
