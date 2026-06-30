package com.template.core.database.util

import java.time.Instant
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class InstantConvertersTest {
    private val converters = InstantConverters()

    @Test
    fun convertsInstantToEpochMillisAndBack() {
        val instant = Instant.parse("2026-06-28T00:00:00Z")

        val epochMillis = converters.instantToEpochMillis(instant)

        assertEquals(1_782_604_800_000L, epochMillis)
        assertEquals(instant, converters.epochMillisToInstant(epochMillis))
    }

    @Test
    fun keepsNullValuesNull() {
        assertNull(converters.instantToEpochMillis(null))
        assertNull(converters.epochMillisToInstant(null))
    }
}
