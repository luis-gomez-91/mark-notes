package com.luisdev.marknotes.features.login

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.HttpStatusCode

class LoginService(
    private val client: HttpClient
) {
//    suspend fun fetchAluFinanzas(id: Int): AluFinanzasResult {
//        return try {
//            val response = client.get("${SERVER_URL}api_rest?action=alu_finanzas&id=$id")
//
//            if (response.status == HttpStatusCode.OK) {
//                val data = response.body<List<Rubro>>()
//                AluFinanzasResult.Success(data)
//            } else {
//                val error = response.body<Error>()
//                AluFinanzasResult.Failure(error)
//            }
//        } catch (e: Exception) {
//            val error = Error("Error", "Error inesperado: ${e.message}")
//            AluFinanzasResult.Failure(error)
//        }
//    }
}