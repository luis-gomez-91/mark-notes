package com.luisdev.marknotes.data.remote.response

sealed class Result<out T> {
    data class Success<out T>(val data: T): Result<T>()
    data class Failure(val error: ErrorResponse): Result<Nothing>()
}