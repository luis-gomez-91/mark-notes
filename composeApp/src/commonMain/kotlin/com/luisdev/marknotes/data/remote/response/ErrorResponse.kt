package com.luisdev.marknotes.data.remote.response

import kotlinx.serialization.Serializable

@Serializable
data class ErrorResponse(
    val code: String? = null,
    val message: String
)