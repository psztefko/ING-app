package com.example.ing_app.ui.posts

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ing_app.common.Result
import com.example.ing_app.common.ResultType
import com.example.ing_app.domain.Comment
import com.example.ing_app.domain.User
import com.example.ing_app.ui.posts.Post as UiPost
import com.example.ing_app.repository.PostRepository
import kotlinx.coroutines.launch
import timber.log.Timber

class PostViewModel(private val postRepository: PostRepository) : ViewModel() {

    private val _postsList = mutableListOf<UiPost>()
    val postsList: List<UiPost>
        get() = _postsList

    private val _posts: MutableLiveData<List<UiPost>> = MutableLiveData()
    val posts: LiveData<List<UiPost>>
        get() = _posts

    private val _navigateToSelectedUser = MutableLiveData<Int>()
    val navigateToSelectedUser: LiveData<Int>
        get() = _navigateToSelectedUser

    private val _navigateToSelectedComments = MutableLiveData<Int>()
    val navigateToSelectedComments: LiveData<Int>
        get() = _navigateToSelectedComments

    private val _isErrorLiveData = MutableLiveData<Boolean>()
    val navigateToErrorScreen: MutableLiveData<Boolean>
        get() = _isErrorLiveData

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val connectionError: MutableLiveData<Int> = MutableLiveData()
    val postsVisibility: MutableLiveData<Int> = MutableLiveData()

    init {
        getPosts()
    }

    //changed to public from private to access from PostFragment
    fun getPosts() {
        viewModelScope.launch {
            //setting visibility to gone after screen refresh
            loadingVisibility.value = View.VISIBLE
            postsVisibility.value = View.GONE
            connectionError.value = View.GONE

            Timber.d("getPosts")
            val apiResultPosts = postRepository.getPosts()
            Timber.d("getComments")
            val apiResultComments = postRepository.getComments()
            Timber.d("getUsers")
            val apiResultUsers = postRepository.getUsers()
            transformPost(apiResultPosts, apiResultComments, apiResultUsers)
        }
    }

    private fun transformPost(
        domainPost: Result<List<com.example.ing_app.domain.Post>>,
        commentResult: Result<List<Comment>>,
        userResult: Result<List<User>>
    ) {
/*        loadingVisibility.value = View.VISIBLE
        postsVisibility.value = View.GONE
        connectionError.value = View.GONE*/
        viewModelScope.launch {
            if(isResultSuccess(domainPost.resultType) &&
               isResultSuccess(userResult.resultType) &&
               isResultSuccess(commentResult.resultType)) {
                  domainPost.data!!.forEach { post ->
                    val userName = userResult.data!!.first { it.id == post.userId }
                    val commentsAmount = commentResult.data!!.filter { it.postId == post.id }
                    val postData = UiPost(
                        id = post.id,
                        userId = post.userId,
                        userName = userName.username,
                        title = post.title,
                        body = post.body,
                        commentsAmount = commentsAmount.size)
                    Timber.d("Postdata = $postData")
                    _postsList.add(postData)
                    if(postsList.size != 0 && postsList.size % 10 == 0) {
                        updatePosts(postsList)
                    }
                 }
            } else {
               onResultError()
            }
        }
    }


    private fun updatePosts(result: List<UiPost>) {
        loadingVisibility.value = View.GONE
        connectionError.value = View.GONE
        postsVisibility.value = View.VISIBLE
        _posts.postValue(result)
    }

    private fun isResultSuccess(resultType: ResultType): Boolean {
        return resultType == ResultType.SUCCESS
    }

    private fun onResultError() {
        Timber.d("onPostsError")
        _isErrorLiveData.postValue(true)
        loadingVisibility.value = View.GONE
        postsVisibility.value = View.GONE
        connectionError.value = View.VISIBLE
    }

    fun onPostUserClicked(id: Int) {
        _navigateToSelectedUser.value = id
    }

    fun onPostCommentClicked(id: Int) {
        _navigateToSelectedComments.value = id
    }

    fun displayUserComplete() {
        _navigateToSelectedUser.value = null
    }

    fun displayCommentsComplete() {
        _navigateToSelectedComments.value = null
    }
}