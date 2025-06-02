package com.luisdev.marknotes.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NoteResponse (
    val id: String,
    @SerialName("user_id") val userId: Int,
    val title: String
)