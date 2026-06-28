package com.template.core.database.metadata

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface DatabaseMetadataDao {
    @Query("SELECT * FROM database_metadata WHERE `key` = :key")
    suspend fun get(key: String): DatabaseMetadataEntity?

    @Upsert suspend fun upsert(metadata: DatabaseMetadataEntity)

    @Query("DELETE FROM database_metadata WHERE `key` = :key") suspend fun delete(key: String)
}
