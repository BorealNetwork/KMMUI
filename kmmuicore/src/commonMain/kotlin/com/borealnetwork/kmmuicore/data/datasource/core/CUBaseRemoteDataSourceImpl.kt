package com.borealnetwork.kmmuicore.data.datasource.core

import com.borealnetwork.kmmuicore.domain.base.EMPTY_STRING
import com.borealnetwork.kmmuicore.domain.base.INDEX_NOT_FOUND
import com.borealnetwork.kmmuicore.domain.network.CUErrorResponseEnum
import com.borealnetwork.kmmuicore.network.core.CUUiState
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import io.ktor.http.Headers
import io.ktor.http.HttpStatusCode
import io.ktor.http.headers
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer

class CUBaseRemoteDataSourceImpl(
    val httpClient: HttpClient,
    val json: Json
) : CUBaseDataSource {

    companion object {
        private var customHeaders = Headers.Empty

        fun addAuthorizationHeaderToken(headers: Headers) {
            customHeaders = headers
        }

        fun getAuthorizationHeaders() = customHeaders
    }

    suspend inline fun <reified T : Any> requestBuilder(
        request: Any? = null,
        urlString: String = EMPTY_STRING,
        hostEnvironment: String= EMPTY_STRING,
        headers: Headers = Headers.Empty,
//        responseModel: KClass<out T>
    ) = try {
        val result = if (request != null) {
            httpClient.post(urlString = hostEnvironment + urlString) {
                this.headers.appendAll(headers)
                headers {
                    append("Content-Type", "application/json")
                    appendAll(getAuthorizationHeaders())
                }
                setBody(request)
            }
        } else {
            httpClient.get(urlString = hostEnvironment + urlString) {
                headers {
                    append("Content-Type", "application/json")
                }
            }
        }
        if (result.status == HttpStatusCode.OK) {
            val jsonString = result.bodyAsText()
            val decodedUser = deserialize<T>(json, jsonString)
            println(decodedUser) // Output: User(id=123, name=Alice, email=alice@example.com)
            CUUiState.Success(decodedUser)
        } else {
            val indexResult = CUErrorResponseEnum.entries.map { it.errorCode }
                .indexOf(result.status.value.toString())
            if (indexResult == INDEX_NOT_FOUND) {
                CUUiState.Error(message = result.bodyAsText())
            } else {

                CUUiState.Error(message = CUErrorResponseEnum.entries[indexResult].messageError)
            }
        }
    } catch (exception: Exception) {
        CUUiState.Error(message = exception.message ?: "Error")
    }

    inline fun <reified T> deserialize(json: Json, jsonString: String): T {
        return json.decodeFromString(serializer<T>(), jsonString)
    }

}