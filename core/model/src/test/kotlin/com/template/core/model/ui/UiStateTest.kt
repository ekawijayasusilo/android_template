package com.template.core.model.ui

import com.template.core.common.result.AppError
import org.junit.Assert.assertEquals
import org.junit.Assert.assertSame
import org.junit.Test

class UiStateTest {
    @Test
    fun loadingUsesSingletonState() {
        val state: UiState<String> = UiState.Loading

        assertSame(UiState.Loading, state)
    }

    @Test
    fun successCarriesData() {
        val state = UiState.Success(data = "value")

        assertEquals("value", state.data)
    }

    @Test
    fun errorCarriesAppError() {
        val error = AppError.Unknown(message = "missing")
        val state: UiState<String> = UiState.Error(error)

        assertSame(error, (state as UiState.Error).error)
    }
}
