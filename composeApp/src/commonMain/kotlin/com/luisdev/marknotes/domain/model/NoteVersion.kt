package com.luisdev.marknotes.domain.model

import kotlinx.datetime.LocalDate
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NoteVersion (
    val id: String,

    @SerialName("note_id")
    val noteId: Int,

    val content: String,

    @SerialName("created_at")
    val createdAt: LocalDate
)