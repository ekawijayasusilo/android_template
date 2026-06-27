package com.template.core.common.coroutines

import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertFalse
import org.junit.Assert.assertSame
import org.junit.Assert.assertTrue
import org.junit.Test

class AppDispatchersTest {
    @Test
    fun storesProvidedDispatchers() = runTest {
        val dispatcher = StandardTestDispatcher(testScheduler)
        val dispatchers =
            CoroutineDispatchers(default = dispatcher, io = dispatcher, main = dispatcher)

        assertSame(dispatcher, dispatchers.default)
        assertSame(dispatcher, dispatchers.io)
        assertSame(dispatcher, dispatchers.main)
    }

    @Test
    fun helperRunsOnConfiguredDispatcher() = runTest {
        val dispatcher = StandardTestDispatcher(testScheduler)
        val dispatchers =
            CoroutineDispatchers(default = dispatcher, io = dispatcher, main = dispatcher)
        var ran = false

        val job = launch { dispatchers.withIo { ran = true } }

        assertFalse(ran)
        testScheduler.advanceUntilIdle()
        assertTrue(ran)
        job.join()
    }
}
