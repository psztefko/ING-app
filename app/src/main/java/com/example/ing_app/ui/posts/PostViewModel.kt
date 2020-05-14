package com.example.ing_app.ui.posts

import android.text.BoringLayout
import android.view.GestureDetector
import android.view.MotionEvent
import androidx.lifecycle.LiveData
import android.view.View
import android.widget.Toast
import androidx.core.view.MotionEventCompat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ing_app.common.Result
import com.example.ing_app.common.ResultType
import com.example.ing_app.domain.Post as DomainPost
import com.example.ing_app.ui.posts.Post as UiPost
import com.example.ing_app.repository.PostRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber
import java.lang.Math.abs

class PostViewModel(private val postRepository: PostRepository) : ViewModel(), GestureDetector.OnGestureListener {

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

    lateinit var gestureDetector: GestureDetector
    var y2 :Float = 0.0f
    var y1 :Float = 0.0f

    init {
        getPosts()
    }

    fun onTouchEvent(event: MotionEvent?): Boolean{
        gestureDetector.onTouchEvent(event)

        when(event?.action){
            0 ->{
                y1 = event.y
            }

            1 ->{
                y2 = event.y

                if(y2 - y1 > 150){
                    Timber.d("onSwipeDown")
                }
            }
        }
        return onTouchEvent(event)
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


    //no need to use them but they have to be implemented
    override fun onShowPress(p0: MotionEvent?) {
    }

    override fun onSingleTapUp(p0: MotionEvent?): Boolean {
        return false
    }

    override fun onDown(p0: MotionEvent?): Boolean {
        return false
    }

    override fun onFling(p0: MotionEvent?, p1: MotionEvent?, p2: Float, p3: Float): Boolean {
        return false
    }

    override fun onScroll(p0: MotionEvent?, p1: MotionEvent?, p2: Float, p3: Float): Boolean {
        return false
    }

    override fun onLongPress(p0: MotionEvent?) {
    }
}

