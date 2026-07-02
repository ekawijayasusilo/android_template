package com.template.core.network.error

import com.template.core.common.result.AppError
import com.template.core.common.result.Result
import com.template.core.common.result.safeApiCall
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.http.HttpStatusCode
import java.io.IOException
import kotlinx.serialization.SerializationException

suspend fun <T> networkResult(block: suspend () -> T): Result<T> =
    safeApiCall(mapError = ::mapNetworkError, block = block)

fun mapNetworkError(throwable: Throwable): AppError =
    when (throwable) {
        is ClientRequestException -> throwable.toClientError()
        is ServerResponseException -> throwable.toRemoteError()
        is ResponseException -> throwable.toRemoteError()
        is IOException -> AppError.Network(message = throwable.message, cause = throwable)
        is SerializationException -> AppError.Remote(message = throwable.message, cause = throwable)
        else -> AppError.Unknown(message = throwable.message, cause = throwable)
    }

private fun ClientRequestException.toClientError(): AppError =
    when (response.status) {
        HttpStatusCode.Unauthorized,
        HttpStatusCode.Forbidden ->
            AppError.Authorization(message = response.status.description, cause = this)
        else -> AppError.Validation(message = response.status.description, cause = this)
    }

private fun ResponseException.toRemoteError(): AppError.Remote =
    AppError.Remote(
        code = response.status.value.toString(),
        message = response.status.description,
        cause = this,
    )
