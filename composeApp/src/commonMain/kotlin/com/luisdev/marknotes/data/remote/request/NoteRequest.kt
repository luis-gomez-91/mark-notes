package com.luisdev.marknotes.data.remote.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NoteRequest(
    @SerialName("user_id")
    val userId: String,

    @SerialName("language_iso")
    val languageIso: String
)