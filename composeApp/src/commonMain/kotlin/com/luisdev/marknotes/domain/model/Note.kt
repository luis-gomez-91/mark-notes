package com.luisdev.marknotes.domain.model

import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Note (
    val id: String,

    @SerialName("user_id")
    val userId: String,

    val title: String,
    val content: String? = null,
    val starred: Boolean = false,

    @SerialName("is_archived")
    val isArchived: Boolean = false,

    @SerialName("is_deleted")
    val isDeleted: Boolean = false,

    @SerialName("folder_id")
    val folderId: String? = null,

    @SerialName("created_at")
    val createdAt: LocalDate,

    @SerialName("updated_at")
    val updatedAt: LocalDate,

    val tags: List<Tag>? = null,
    val versions: List<NoteVersion>? = null
)