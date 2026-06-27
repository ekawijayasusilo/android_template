package com.template.core.model.ui

import com.template.core.common.result.AppError

sealed interface UiState<out T> {
    data object Loading : UiState<Nothing>

    data class Success<out T>(val data: T) : UiState<T>

    data class Error(val error: AppError) : UiState<Nothing>
}
