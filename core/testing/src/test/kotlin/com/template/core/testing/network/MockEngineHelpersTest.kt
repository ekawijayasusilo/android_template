package com.template.core.testing.network

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class MockEngineHelpersTest {
    @Test
    fun jsonMockEngineReturnsBodyAndExposesRequest() = runTest {
        var requestedPath = ""
        val client =
            HttpClient(
                jsonMockEngine(body = "{\"ok\":true}") { request ->
                    requestedPath = request.url.encodedPath
                }
            )

        val body = client.get("https://example.test/items").bodyAsText()

        assertEquals("/items", requestedPath)
        assertEquals("{\"ok\":true}", body)
        client.close()
    }
}
