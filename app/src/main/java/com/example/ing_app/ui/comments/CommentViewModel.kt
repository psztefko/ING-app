package com.example.ing_app.ui.comments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ing_app.common.ResultType
import com.example.ing_app.common.Result
import com.example.ing_app.domain.Comment
import com.example.ing_app.repository.CommentRepository
import kotlinx.coroutines.launch

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

    init {
        getComments()
    }

    fun getComments(){
        viewModelScope.launch {
            val apiResult = commentRepository.getCommentsFromPost(commentKey)
            updateComments(apiResult)
        }
    }

    private fun updateComments(result: Result<List<Comment>>) {
        if (isResultSuccess(result.resultType)) {
            _comments.postValue(result.data)
        } else {
            onResultError()
        }

    }

    fun doneNavigating() {
        _navigateToPosts.value = null
    }

    fun onClose() {
        _navigateToPosts.value = true
    }

    private fun isResultSuccess(resultType: ResultType): Boolean {
        return resultType == ResultType.SUCCESS
    }

    private fun onResultError() = _isErrorLiveData.postValue(true)
}