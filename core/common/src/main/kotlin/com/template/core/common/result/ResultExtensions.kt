package com.template.core.common.result

inline fun <T, R> Result<T>.map(transform: (T) -> R): Result<R> =
    when (this) {
        is Result.Success -> Result.Success(transform(value))
        is Result.Error -> this
    }

inline fun <T, R> Result<T>.fold(onSuccess: (T) -> R, onError: (AppError) -> R): R =
    when (this) {
        is Result.Success -> onSuccess(value)
        is Result.Error -> onError(error)
    }
