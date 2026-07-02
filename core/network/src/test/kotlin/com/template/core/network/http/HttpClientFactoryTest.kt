package com.template.core.network.http

import com.template.core.network.auth.BearerTokensProvider
import com.template.core.network.auth.NetworkBearerTokens
import com.template.core.testing.network.jsonMockEngine
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class HttpClientFactoryTest {
    @Test
    fun clientAttachesBearerTokenAndReadsJson() = runTest {
        var authorizationHeader = ""
        val engine =
            jsonMockEngine(body = "{\"ok\":true}") { request ->
                authorizationHeader = request.headers["Authorization"].orEmpty()
            }
        val client =
            createHttpClient(
                engine = engine,
                bearerTokensProvider =
                    object : BearerTokensProvider {
                        override suspend fun loadTokens(): NetworkBearerTokens =
                            NetworkBearerTokens(accessToken = "access-token")
                    },
            )

        val body = client.get("https://example.test/resource").bodyAsText()

        assertEquals("Bearer access-token", authorizationHeader)
        assertEquals("{\"ok\":true}", body)
        client.close()
    }
}
