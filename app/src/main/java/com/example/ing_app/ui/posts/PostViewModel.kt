package com.example.ing_app.ui.posts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ing_app.common.Result
import com.example.ing_app.common.ResultType
import com.example.ing_app.domain.Post as DomainPost
import com.example.ing_app.ui.posts.Post as UiPost
import com.example.ing_app.repository.PostRepository
import kotlinx.coroutines.launch
import timber.log.Timber


class PostViewModel(private val postRepository: PostRepository) : ViewModel() {
    private val _posts: MutableLiveData<List<UiPost>> = MutableLiveData()
    val posts: LiveData<List<UiPost>>
        get() = _posts

    private val _navigateToSelectedUser = MutableLiveData<Int>()
    val navigateToSelectedUser: LiveData<Int>
        get() = _navigateToSelectedUser

    private val _navigateToSelectedComments = MutableLiveData<UiPost>()
    val navigateToSelectedComments: LiveData<UiPost>
        get() = _navigateToSelectedComments

    init {
        getPosts()
    }

    private fun getPosts() {
        viewModelScope.launch {
            Timber.d("getPosts")
            val apiResult = postRepository.getPosts()
            transformPost(apiResult)
        }
    }

    private fun transformPost(domainPost: Result<List<DomainPost>>) {
        val uiPostList: MutableList<UiPost> = mutableListOf()
        viewModelScope.launch {
            // Better way but still can do better
            var temporaryUserId = domainPost.data!![0].userId
            var userResult = postRepository.getUserFromPost(temporaryUserId)
            for(post in domainPost.data) {
                val commentResult = postRepository.getCommentsFromPost(post.id)
                if (post.userId != temporaryUserId) {
                    temporaryUserId = post.userId
                    userResult = postRepository.getUserFromPost(temporaryUserId)
                }
                if(isResultSuccess(userResult.resultType) && isResultSuccess(commentResult.resultType)) {
                    val postData = UiPost(
                        id = post.id,
                        userId = post.userId,
                        userName = userResult.data!!.username,
                        title = post.title,
                        body = post.body,
                        commentsAmount = commentResult.data!!.size
                    )
                    Timber.d("Postdata = $postData")
                    uiPostList.add(postData)
                }
                updatePosts(uiPostList.toList())
            }
        }
    }

    private fun updatePosts(result: List<UiPost>) {
        _posts.postValue(result)
    }

    private fun isResultSuccess(resultType: ResultType): Boolean {
        return resultType == ResultType.SUCCESS
    }

    fun onPostUserClicked(id: Int) {
        _navigateToSelectedUser.value = id
    }

    fun displayUserComplete() {
        _navigateToSelectedUser.value = null
    }

    fun displayCommentsComplete() {
        _navigateToSelectedComments.value = null
    }
}