package com.template.core.network.http

import com.template.core.network.auth.BearerTokensProvider
import com.template.core.network.auth.NetworkBearerTokens
import com.template.core.network.auth.NoOpBearerTokensProvider
import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import java.util.concurrent.TimeUnit
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient

private const val CONNECT_TIMEOUT_SECONDS = 10L
private const val IO_TIMEOUT_SECONDS = 30L
private const val CALL_TIMEOUT_SECONDS = 30L

fun defaultNetworkJson(): Json = Json {
    ignoreUnknownKeys = true
    isLenient = true
    encodeDefaults = true
}

fun buildSharedOkHttpClient(): OkHttpClient =
    OkHttpClient.Builder()
        .connectTimeout(CONNECT_TIMEOUT_SECONDS, TimeUnit.SECONDS)
        .readTimeout(IO_TIMEOUT_SECONDS, TimeUnit.SECONDS)
        .writeTimeout(IO_TIMEOUT_SECONDS, TimeUnit.SECONDS)
        .callTimeout(CALL_TIMEOUT_SECONDS, TimeUnit.SECONDS)
        .build()

fun createOkHttpHttpClient(
    okHttpClient: OkHttpClient = buildSharedOkHttpClient(),
    json: Json = defaultNetworkJson(),
    bearerTokensProvider: BearerTokensProvider = NoOpBearerTokensProvider,
    configure: HttpClientConfig<*>.() -> Unit = {},
): HttpClient =
    HttpClient(OkHttp) {
        engine { preconfigured = okHttpClient }
        configureSharedClient(json, bearerTokensProvider)
        configure()
    }

fun createHttpClient(
    engine: HttpClientEngine,
    json: Json = defaultNetworkJson(),
    bearerTokensProvider: BearerTokensProvider = NoOpBearerTokensProvider,
    configure: HttpClientConfig<*>.() -> Unit = {},
): HttpClient =
    HttpClient(engine) {
        configureSharedClient(json, bearerTokensProvider)
        configure()
    }

private fun HttpClientConfig<*>.configureSharedClient(
    json: Json,
    bearerTokensProvider: BearerTokensProvider,
) {
    expectSuccess = true
    install(ContentNegotiation) { json(json) }
    install(Auth) {
        bearer {
            sendWithoutRequest { true }
            loadTokens { bearerTokensProvider.loadTokens()?.toKtorBearerTokens() }
        }
    }
    HttpResponseValidator {}
}

private fun NetworkBearerTokens.toKtorBearerTokens(): BearerTokens =
    BearerTokens(accessToken = accessToken, refreshToken = refreshToken.orEmpty())
