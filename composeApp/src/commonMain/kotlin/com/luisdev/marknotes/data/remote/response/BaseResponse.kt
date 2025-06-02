package com.luisdev.marknotes.data.remote.response

import kotlinx.serialization.Serializable

@Serializable
data class BaseResponse<T>(
    val status: String,
    val data: T? = null,
    val error: ErrorResponse? = null
)