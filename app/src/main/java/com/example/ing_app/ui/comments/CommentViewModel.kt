package com.example.ing_app.ui.comments

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ing_app.common.ResultType
import com.example.ing_app.common.Result
import com.example.ing_app.domain.Comment
import com.example.ing_app.repository.CommentRepository
import kotlinx.coroutines.launch
import timber.log.Timber

class CommentViewModel (private val commentKey: Int = 0,
                        private val commentRepository: CommentRepository): ViewModel() {
    private val _comments: MutableLiveData<List<Comment>> = MutableLiveData()
    val comments: LiveData<List<Comment>>
        get() = _comments
    private val _isErrorLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val isErrorLiveData: LiveData<Boolean>
        get() = _isErrorLiveData

    private val _navigateToPosts = MutableLiveData<Boolean?>()
    val navigateToPosts: LiveData<Boolean?>
        get() = _navigateToPosts

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val connectionError: MutableLiveData<Int> = MutableLiveData()
    val commentsVisibility: MutableLiveData<Int> = MutableLiveData()

    init {
        getComments()
    }

    fun getComments(){
        loadingVisibility.value = View.VISIBLE
        commentsVisibility.value = View.GONE
        connectionError.value = View.GONE
        viewModelScope.launch {
            val apiResult = commentRepository.getCommentsFromPost(commentKey)
            updateComments(apiResult)
        }
    }

    private fun updateComments(result: Result<List<Comment>>) {
        loadingVisibility.value = View.GONE
        if (isResultSuccess(result.resultType)) {
            _comments.postValue(result.data)
            commentsVisibility.value = View.VISIBLE
        } else {
            onResultError()
        }
    }

    private fun isResultSuccess(resultType: ResultType): Boolean {
        return resultType == ResultType.SUCCESS
    }

    fun doneNavigating() {
        _navigateToPosts.value = null
    }

    fun onClose() {
        _navigateToPosts.value = true
    }


    private fun onResultError() {
        Timber.d("onCommentsError")
        _isErrorLiveData.postValue(true)
        loadingVisibility.value = View.GONE
        commentsVisibility.value = View.GONE
        connectionError.value = View.VISIBLE
    }
}