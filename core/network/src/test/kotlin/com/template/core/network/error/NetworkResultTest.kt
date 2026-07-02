package com.template.core.network.error

import com.template.core.common.result.AppError
import com.template.core.common.result.Result
import com.template.core.network.http.createHttpClient
import com.template.core.testing.network.jsonMockEngine
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpStatusCode
import java.io.IOException
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class NetworkResultTest {
    @Test
    fun mapsUnauthorizedResponsesToAuthorizationError() = runTest {
        val client =
            createHttpClient(jsonMockEngine(body = "{}", status = HttpStatusCode.Unauthorized))

        val result = networkResult { client.get("https://example.test/private").bodyAsText() }

        assertTrue((result as Result.Error).error is AppError.Authorization)
        client.close()
    }

    @Test
    fun mapsServerResponsesToRemoteErrorWithStatusCode() = runTest {
        val client =
            createHttpClient(
                jsonMockEngine(body = "{}", status = HttpStatusCode.InternalServerError)
            )

        val result = networkResult { client.get("https://example.test/resource").bodyAsText() }

        assertEquals("500", (result as Result.Error).error.let { it as AppError.Remote }.code)
        client.close()
    }

    @Test
    fun mapsIoFailuresToNetworkError() = runTest {
        val client = createHttpClient(MockEngine { throw IOException("offline") })

        val result = networkResult { client.get("https://example.test/resource").bodyAsText() }

        assertTrue((result as Result.Error).error is AppError.Network)
        client.close()
    }
}
