package com.template.core.common.result

import kotlinx.coroutines.CancellationException

@Suppress("TooGenericExceptionCaught")
suspend fun <T> safeApiCall(
    mapError: (Throwable) -> AppError = { AppError.Unknown(cause = it) },
    block: suspend () -> T,
): Result<T> =
    try {
        Result.Success(block())
    } catch (cancellation: CancellationException) {
        throw cancellation
    } catch (exception: Exception) {
        Result.Error(mapError(exception))
    }
