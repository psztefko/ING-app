package com.example.ing_app.ui.posts

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ing_app.common.Result
import com.example.ing_app.common.ResultType
import com.example.ing_app.domain.Post
import com.example.ing_app.repository.PostRepository
import kotlinx.coroutines.launch
import timber.log.Timber


class PostViewModel(private val postRepository: PostRepository) : ViewModel() {
    val posts: MutableLiveData<List<Post>> = MutableLiveData()

    init {
        getPosts()
    }

    fun getPosts() {
        viewModelScope.launch {
            Timber.d("getPosts")
            val apiResult = postRepository.getPosts()
            updatePosts(apiResult)
        }
    }

    private fun updatePosts(result: Result<List<Post>>) {
        if (isResultSuccess(result.resultType)) {
            Timber.d("updatePosts")
            posts.postValue(result.data)
        }
    }

    private fun isResultSuccess(resultType: ResultType): Boolean {
        return resultType == ResultType.SUCCESS
    }
}