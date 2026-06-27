package com.template.core.common.result

import org.junit.Assert.assertEquals
import org.junit.Assert.assertSame
import org.junit.Test

class ResultTest {
    @Test
    fun successPreservesPayloadThroughMapAndFold() {
        val result: Result<Int> = Result.Success(2)

        assertEquals(Result.Success(4), result.map { it * 2 })
        assertEquals("value=2", result.fold(onSuccess = { "value=$it" }, onError = { "error" }))
    }

    @Test
    fun errorPreservesTypedErrorThroughMapAndFold() {
        val error = AppError.Remote(code = "remote")
        val result: Result<Int> = Result.Error(error)

        assertEquals(Result.Error(error), result.map { it * 2 })
        assertSame(
            error,
            result.fold(onSuccess = { AppError.Unknown(message = "unexpected") }, onError = { it }),
        )
    }
}
