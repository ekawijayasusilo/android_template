package com.template.core.common.result

sealed interface Result<out T> {
    data class Success<out T>(val value: T) : Result<T>

    data class Error(val error: AppError) : Result<Nothing>
}
