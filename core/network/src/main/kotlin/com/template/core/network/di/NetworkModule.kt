package com.template.core.network.di

import com.template.core.network.auth.BearerTokensProvider
import com.template.core.network.auth.NoOpBearerTokensProvider
import com.template.core.network.http.buildSharedOkHttpClient
import com.template.core.network.http.createOkHttpHttpClient
import com.template.core.network.http.defaultNetworkJson
import io.ktor.client.HttpClient
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import org.koin.dsl.module

val coreNetworkModule = module {
    single<Json> { defaultNetworkJson() }
    single<OkHttpClient> { buildSharedOkHttpClient() }
    single<BearerTokensProvider> { NoOpBearerTokensProvider }
    single<HttpClient> {
        createOkHttpHttpClient(okHttpClient = get(), json = get(), bearerTokensProvider = get())
    }
}
