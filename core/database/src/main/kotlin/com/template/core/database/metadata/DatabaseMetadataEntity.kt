package com.template.core.database.metadata

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "database_metadata")
data class DatabaseMetadataEntity(
    @PrimaryKey val key: String,
    val value: String,
    @ColumnInfo(name = "updated_at_epoch_millis") val updatedAtEpochMillis: Long,
)
