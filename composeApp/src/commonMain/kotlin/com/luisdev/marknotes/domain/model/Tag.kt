package com.luisdev.marknotes.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Tag (
    val id: String,

    @SerialName("user_id")
    val userId: String,
    val name: String,
)