package com.luisdev.marknotes.data.remote.service

import com.luisdev.marknotes.data.remote.response.Result
import com.luisdev.marknotes.core.utils.URL_SERVER
import com.luisdev.marknotes.data.remote.request.NoteRequest
import com.luisdev.marknotes.data.remote.response.BaseResponse
import com.luisdev.marknotes.data.remote.response.ErrorResponse
import com.luisdev.marknotes.data.remote.response.NoteResponse
import com.luisdev.marknotes.domain.model.Note
import io.github.aakira.napier.Napier
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType

class NotesService(
    private val client: HttpClient
) {
    suspend fun createNote(body: NoteRequest, token: String): Result<NoteResponse> {
        return try {
            Napier.i("TOKEN: $token")
            val response = client.post("${URL_SERVER}notas/") {
                headers {
                    append(HttpHeaders.Authorization, "Bearer $token")
                }
                contentType(ContentType.Application.Json)
                setBody(body)
            }
            Napier.i("Respuesta recibida con status: ${response.status.value}", tag = "NotesService")

            if (response.status.value in 200..299) {
                Napier.i("Status OK, intentando parsear BaseResponse", tag = "NotesService")
                val baseResponse = response.body<BaseResponse<NoteResponse>>()
                if (baseResponse.status == "success" && baseResponse.data != null) {
                    Napier.i("Parseo exitoso, retornando Success", tag = "NotesService")
                    Result.Success(baseResponse.data)
                } else {
                    Napier.i("Error en respuesta del backend: ${baseResponse.error}", tag = "NotesService")
                    Result.Failure(
                        baseResponse.error ?: ErrorResponse("Error", "Respuesta inválida del servidor")
                    )
                }
            } else {
                val errorBody = response.body<String>()
                Napier.i("Error HTTP ${response.status.value}: $errorBody", tag = "NotesService")
                Result.Failure(ErrorResponse("Error HTTP ${response.status.value}", errorBody))
            }
        } catch (e: Exception) {
            Napier.i("Excepción capturada: ${e.message}", tag = "NotesService")
            Result.Failure(ErrorResponse("Error", "Error inesperado: ${e.message}"))
        }
    }

    suspend fun fetchNotes(token: String): Result<List<Note>> {
        return try {
            Napier.i("TOKEN: $token")
            val response = client.get("${URL_SERVER}notas") {
                headers {
                    append(HttpHeaders.Authorization, "Bearer $token")
                }
                contentType(ContentType.Application.Json)
            }
            Napier.i("Respuesta recibida con status: ${response.status.value}", tag = "NotesService")

            if (response.status.value in 200..299) {
                Napier.i("Status OK, intentando parsear BaseResponse", tag = "NotesService")
                val baseResponse = response.body<BaseResponse<List<Note>>>()
                if (baseResponse.status == "success" && baseResponse.data != null) {
                    Napier.i("Parseo exitoso, retornando Success", tag = "NotesService")
                    Result.Success(baseResponse.data)
                } else {
                    Napier.i("Error en respuesta del backend: ${baseResponse.error}", tag = "NotesService")
                    Result.Failure(
                        baseResponse.error ?: ErrorResponse("Error", "Respuesta inválida del servidor")
                    )
                }
            } else {
                val errorBody = response.body<String>()
                Napier.i("Error HTTP ${response.status.value}: $errorBody", tag = "NotesService")
                Result.Failure(ErrorResponse("Error HTTP ${response.status.value}", errorBody))
            }
        } catch (e: Exception) {
            Napier.i("Excepción capturada: ${e.message}", tag = "NotesService")
            Result.Failure(ErrorResponse("Error", "Error inesperado: ${e.message}"))
        }
    }

}


