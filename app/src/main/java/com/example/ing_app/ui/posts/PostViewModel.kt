package com.example.ing_app.ui.posts

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ing_app.common.Result
import com.example.ing_app.common.ResultType
import com.example.ing_app.repository.PostRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber
import com.example.ing_app.domain.Post as DomainPost
import com.example.ing_app.ui.posts.Post as UiPost

class PostViewModel(private val postRepository: PostRepository) : ViewModel() {

    private val _posts: MutableLiveData<List<UiPost>> = MutableLiveData()
    val posts: LiveData<List<UiPost>>
        get() = _posts

    private val _navigateToSelectedUser = MutableLiveData<Int>()
    val navigateToSelectedUser: LiveData<Int>
        get() = _navigateToSelectedUser

    private val _navigateToSelectedComments = MutableLiveData<Int>()
    val navigateToSelectedComments: LiveData<Int>
        get() = _navigateToSelectedComments

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val connectionError: MutableLiveData<Int> = MutableLiveData()
    val postsVisibility: MutableLiveData<Int> = MutableLiveData()

    init {
        getPosts()
    }

    private fun getPosts() {
        viewModelScope.launch {
            Timber.d("getPosts")
            val apiResult = postRepository.getPosts()
            transformPost(apiResult)
            postsVisibility.value = View.VISIBLE
        }
    }

    private fun transformPost(domainPost: Result<List<DomainPost>>) {

        val uiPostList: MutableList<UiPost> = mutableListOf()
        loadingVisibility.value = View.VISIBLE
        viewModelScope.launch {
            // Better way but still can do better
            var temporaryUserId = domainPost.data?.get(0)?.userId
            if(temporaryUserId == null){
                loadingVisibility.value = View.GONE
                postsVisibility.value = View.GONE
                connectionError.value = View.VISIBLE

                while (true){
                    delay(500)
                }
            }
            var userResult = temporaryUserId?.let { postRepository.getUserFromPost(it) }
            domainPost.data?.forEach { post ->
                val commentResult = postRepository.getCommentsFromPost(post.id)
                if (post.userId != temporaryUserId) {
                    temporaryUserId = post.userId
                    userResult = postRepository.getUserFromPost(temporaryUserId!!)
                }
                if(userResult?.resultType?.let { isResultSuccess(it) }!! && isResultSuccess(commentResult.resultType)) {
                    val postData = UiPost(
                        id = post.id,
                        userId = post.userId,
                        userName = userResult?.data!!.username,
                        title = post.title,
                        body = post.body,
                        commentsAmount = commentResult.data!!.size
                    )
                    Timber.d("Postdata = $postData")
                    uiPostList.add(postData)
                }else{
                    loadingVisibility.value = View.GONE
                    postsVisibility.value = View.GONE
                    connectionError.value = View.VISIBLE
                }
                updatePosts(uiPostList.toList())
            }
        }
    }


    private fun updatePosts(result: List<UiPost>) {
        loadingVisibility.value = View.GONE
        _posts.postValue(result)
    }

    private fun isResultSuccess(resultType: ResultType): Boolean {
        return resultType == ResultType.SUCCESS
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

