package com.template.core.database.migration

import androidx.room.RoomDatabase
import androidx.room.migration.Migration

object DatabaseMigrations {
    val all: Array<Migration> = emptyArray()

    fun requireUniquePaths(migrations: Array<Migration> = all): Array<Migration> {
        val paths = mutableSetOf<Pair<Int, Int>>()
        migrations.forEach { migration ->
            val path = migration.startVersion to migration.endVersion
            require(paths.add(path)) {
                "Duplicate Room migration path ${path.first}->${path.second}"
            }
        }
        return migrations
    }
}

@Suppress("SpreadOperator")
fun <T : RoomDatabase> RoomDatabase.Builder<T>.addAppMigrations(
    migrations: Array<Migration> = DatabaseMigrations.all
): RoomDatabase.Builder<T> = addMigrations(*DatabaseMigrations.requireUniquePaths(migrations))
