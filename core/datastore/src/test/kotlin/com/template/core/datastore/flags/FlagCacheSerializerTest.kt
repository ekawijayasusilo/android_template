package com.template.core.datastore.flags

import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class FlagCacheSerializerTest {
    @Test
    fun returnsDefaultForEmptyInput() = runTest {
        val result = FlagCacheSerializer.readFrom(ByteArrayInputStream(ByteArray(0)))

        assertEquals(FlagCache(), result)
    }

    @Test
    fun writesAndReadsFlagCache() = runTest {
        val cache =
            FlagCache(
                entries =
                    mapOf(
                        "sample_enabled" to
                            CachedFlag(
                                value = "true",
                                source = FlagSource.LocalOverride,
                                updatedAtEpochMillis = 123L,
                            )
                    ),
                updatedAtEpochMillis = 456L,
            )
        val output = ByteArrayOutputStream()

        FlagCacheSerializer.writeTo(cache, output)
        val result = FlagCacheSerializer.readFrom(ByteArrayInputStream(output.toByteArray()))

        assertEquals(cache, result)
    }
}
