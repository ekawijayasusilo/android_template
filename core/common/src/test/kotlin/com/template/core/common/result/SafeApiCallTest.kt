package com.template.core.common.result

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertSame
import org.junit.Assert.fail
import org.junit.Test

class SafeApiCallTest {
    @Test
    fun returnsSuccessForCompletedWork() = runTest {
        val result = safeApiCall { "payload" }

        assertEquals(Result.Success("payload"), result)
    }

    @Test
    fun mapsThrownFailures() = runTest {
        val failure = IllegalStateException("boom")
        val mapped = AppError.Validation(message = "invalid", cause = failure)

        val result = safeApiCall(mapError = { mapped }) { throw failure }

        assertEquals(Result.Error(mapped), result)
    }

    @Test
    fun rethrowsCancellation() = runTest {
        val cancellation = CancellationException("cancelled")

        try {
            safeApiCall { throw cancellation }
            fail("Expected cancellation to be rethrown")
        } catch (actual: CancellationException) {
            assertSame(cancellation, actual)
        }
    }
}
