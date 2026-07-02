package com.template.core.testing.network

import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.request.HttpRequestData
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf

fun jsonMockEngine(
    body: String,
    status: HttpStatusCode = HttpStatusCode.OK,
    assertRequest: (HttpRequestData) -> Unit = {},
): MockEngine = MockEngine { request ->
    assertRequest(request)
    respond(
        content = body,
        status = status,
        headers = headersOf(HttpHeaders.ContentType, ContentType.Application.Json.toString()),
    )
}
