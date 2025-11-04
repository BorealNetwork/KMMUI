package com.borealnetwork.kmmuicore.di.network

import com.borealnetwork.kmmuicore.data.datasource.core.CUBaseRemoteDataSourceImpl.Companion.getAuthorizationHeaders
import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.observer.ResponseObserver
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val networkModule = module {

    single {
        Json {
            ignoreUnknownKeys = true
            isLenient = true
            encodeDefaults = false
            prettyPrint = true
            useArrayPolymorphism = true
            allowStructuredMapKeys = true
            allowSpecialFloatingPointValues = true
            coerceInputValues = true
            explicitNulls = false
        }
    }
    single {
        HttpClient {
            install(ContentNegotiation) {
                json(json = get(), contentType = ContentType.Any)
            }
            install(Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        println("Logger Ktor => $message")
                    }

                }
                level = LogLevel.ALL
            }
            install(ResponseObserver) {
                onResponse { response ->
                    println("HTTP status: ${response.status.value}")
                }
            }
            install(DefaultRequest) {
                header(HttpHeaders.ContentType, ContentType.Application.Json)
                headers.appendAll(getAuthorizationHeaders())
            }
        }
    }

}

