package com.example.ing_app.common

data class Result<out T>(
    var resultType: ResultType,
    val data: T? = null,
    val failure: Exception? = null
) {
    companion object {
        fun <T> success(data: T?): Result<T> {
            return Result(ResultType.SUCCESS, data)
        }

        fun <T> failure(failure: Exception? = null): Result<T> {
            return Result(ResultType.FAILURE, failure = failure)
        }
    }
}