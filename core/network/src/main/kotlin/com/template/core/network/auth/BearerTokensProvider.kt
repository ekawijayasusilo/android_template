package com.template.core.network.auth

data class NetworkBearerTokens(val accessToken: String, val refreshToken: String? = null)

interface BearerTokensProvider {
    suspend fun loadTokens(): NetworkBearerTokens?
}

object NoOpBearerTokensProvider : BearerTokensProvider {
    override suspend fun loadTokens(): NetworkBearerTokens? = null
}
