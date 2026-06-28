package com.template.core.database.migration

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import org.junit.Assert.assertSame
import org.junit.Assert.assertTrue
import org.junit.Assert.fail
import org.junit.Test

class DatabaseMigrationsTest {
    @Test
    fun returnsUniqueMigrationPaths() {
        val migration = testMigration(startVersion = 1, endVersion = 2)
        val migrations = arrayOf(migration)

        assertSame(migrations, DatabaseMigrations.requireUniquePaths(migrations))
    }

    @Test
    fun rejectsDuplicateMigrationPaths() {
        val migrations = arrayOf(testMigration(1, 2), testMigration(1, 2))

        try {
            DatabaseMigrations.requireUniquePaths(migrations)
            fail("Expected duplicate migration paths to fail")
        } catch (exception: IllegalArgumentException) {
            assertTrue(exception.message.orEmpty().contains("Duplicate Room migration path"))
        }
    }

    private fun testMigration(startVersion: Int, endVersion: Int): Migration =
        object : Migration(startVersion, endVersion) {
            override fun migrate(db: SupportSQLiteDatabase) = Unit
        }
}
