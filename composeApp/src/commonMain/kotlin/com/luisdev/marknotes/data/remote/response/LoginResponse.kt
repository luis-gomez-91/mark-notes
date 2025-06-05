package com.luisdev.marknotes.data.remote.response

import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
    val access_token: String,
    val token_type: String,
    val user_id: String,
    val email: String? = null
)