package com.borealnetwork.kmmuicore.data.datasource.core

import com.borealnetwork.kmmuicore.domain.base.EMPTY_STRING
import com.borealnetwork.kmmuicore.domain.base.INDEX_NOT_FOUND
import com.borealnetwork.kmmuicore.domain.error.CUNetworkErrorsEnum
import com.borealnetwork.kmmuicore.domain.error.CUNetworkResult
import com.borealnetwork.kmmuicore.domain.network.CUErrorResponseEnum
import com.borealnetwork.kmmuicore.domain.network.error_models.ErrorBaseResponseV1
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
        hostEnvironment: String = EMPTY_STRING,
        headers: Headers = Headers.Empty,
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
                CUUiState.Error(
                    message = CUErrorResponseEnum.entries[indexResult].messageError,
                    error = result.bodyAsText()
                )
            }
        }
    } catch (exception: Exception) {
        CUUiState.Error(message = exception.message ?: "Error")
    }

    suspend inline fun <reified T : Any> requestBuilderV1(
        request: Any? = null,
        urlString: String = EMPTY_STRING,
        hostEnvironment: String = EMPTY_STRING,
        headers: Headers = Headers.Empty,
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

        if (result.status != HttpStatusCode.OK) {
            val errorResponse = json.decodeFromString<ErrorBaseResponseV1>(result.bodyAsText())
            val errorResult = handleHttpError(
                code = result.status.value,
                message = errorResponse.descripcion,
                errorBody = result.bodyAsText()
            )

            CUUiState.Error(
                error = errorResult.errorBody,
                message = errorResult.message
            )
        } else if (result.status == HttpStatusCode.OK) {
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
                CUUiState.Error(
                    message = CUErrorResponseEnum.entries[indexResult].messageError,
                    error = result.bodyAsText()
                )
            }
        }
    } catch (exception: Exception) {
        CUUiState.Error(message = exception.message ?: "Error")
    }

    inline fun <reified T> deserialize(json: Json, jsonString: String): T {
        return json.decodeFromString(serializer<T>(), jsonString)
    }

}

fun handleHttpError( // todo se puede mejorar con un filtro de errores en lugar de un when gigante
    code: Int,
    message: String?,
    errorBody: String?
): CUNetworkResult.HttpError =
    when (code) {
        400, 401 -> {
            println("Unauthorized - 401")
            CUNetworkResult.HttpError(
                code = code,
                message = "${CUNetworkErrorsEnum.AUTHORIZATION_ERROR.message} $message",
                errorBody = errorBody
            )
        }

        404 -> {
            println("Not Found - 404")
            CUNetworkResult.HttpError(
                code = code,
                message = "${CUNetworkErrorsEnum.URL_NOT_FOUND_ERROR.message} $message",
                errorBody = errorBody
            )
        }

        415 -> {
            println("Unsupported Media Type - 415")
            CUNetworkResult.HttpError(
                code = code,
                message = "${CUNetworkErrorsEnum.UNSUPPORTED_MEDIA_TYPE_ERROR.message} $message",
                errorBody = errorBody
            )
        }

        426 -> {
            println("Unsupported Media Type - 426")
            CUNetworkResult.HttpError(
                code = code,
                message = "${CUNetworkErrorsEnum.UNSUPPORTED_MEDIA_TYPE_ERROR_426.message} $message",
                errorBody = errorBody
            )
        }

        429 -> {
            println("Too Many Requests - 429")
            CUNetworkResult.HttpError(
                code = code,
                message = "${CUNetworkErrorsEnum.TO_MANY_REQUESTS.message} $message",
                errorBody = errorBody
            )
        }

        500 -> {
            println("Internal Server Error - 500")
            CUNetworkResult.HttpError(
                code = code,
                message = "${CUNetworkErrorsEnum.SERVER_ERROR_500.message} $message",
                errorBody = errorBody
            )
        }

        502 -> {
            println("Service Unavailable - 502")
            CUNetworkResult.HttpError(
                code = code,
                message = "${CUNetworkErrorsEnum.SERVER_ERROR_502.message} $message",
                errorBody = errorBody
            )
        }

        503 -> {
            println("Service Unavailable - 503")
            CUNetworkResult.HttpError(
                code = code,
                message = "${CUNetworkErrorsEnum.SERVER_ERROR_503.message} $message",
                errorBody = errorBody
            )
        }

        504 -> {
            println("Gateway Timeout - 504")
            CUNetworkResult.HttpError(
                code = code,
                message = "${CUNetworkErrorsEnum.TIMEOUT_ERROR.message} $message",
                errorBody = errorBody
            )
        }

        else -> {
            CUNetworkResult.HttpError(
                code = code,
                message = message ?: "Unknown Error",
                errorBody = errorBody
            )
        }
    }