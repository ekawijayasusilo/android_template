package com.template.core.datastore.flags

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import java.io.InputStream
import java.io.OutputStream
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationException
import kotlinx.serialization.protobuf.ProtoBuf

@Serializable
data class FlagCache(
    val entries: Map<String, CachedFlag> = emptyMap(),
    val updatedAtEpochMillis: Long = 0L,
)

@Serializable
data class CachedFlag(
    val value: String,
    val source: FlagSource = FlagSource.Remote,
    val updatedAtEpochMillis: Long,
)

@Serializable
enum class FlagSource {
    Remote,
    LocalOverride,
}

@OptIn(ExperimentalSerializationApi::class)
object FlagCacheSerializer : Serializer<FlagCache> {
    override val defaultValue: FlagCache = FlagCache()

    override suspend fun readFrom(input: InputStream): FlagCache =
        try {
            val bytes = input.readBytes()
            if (bytes.isEmpty()) defaultValue
            else ProtoBuf.decodeFromByteArray(FlagCache.serializer(), bytes)
        } catch (exception: SerializationException) {
            throw CorruptionException("Cannot read flag cache", exception)
        }

    override suspend fun writeTo(t: FlagCache, output: OutputStream) {
        output.write(ProtoBuf.encodeToByteArray(FlagCache.serializer(), t))
    }
}
