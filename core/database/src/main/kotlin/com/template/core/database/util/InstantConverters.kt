package com.template.core.database.util

import androidx.room.TypeConverter
import java.time.Instant

class InstantConverters {
    @TypeConverter fun instantToEpochMillis(value: Instant?): Long? = value?.toEpochMilli()

    @TypeConverter
    fun epochMillisToInstant(value: Long?): Instant? = value?.let(Instant::ofEpochMilli)
}
