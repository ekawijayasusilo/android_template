package com.template.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.template.core.database.metadata.DatabaseMetadataDao
import com.template.core.database.metadata.DatabaseMetadataEntity
import com.template.core.database.util.InstantConverters

@Database(
    entities = [DatabaseMetadataEntity::class],
    version = AppDatabase.VERSION,
    exportSchema = true,
)
@TypeConverters(InstantConverters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun metadataDao(): DatabaseMetadataDao

    companion object {
        const val VERSION = 1
        const val NAME = "template.db"
    }
}
