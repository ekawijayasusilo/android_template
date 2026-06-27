package com.template.core.model.paging

import com.template.core.model.ui.UiState
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Test

class PagingModelTest {
    @Test
    fun offsetKeyStoresBounds() {
        val key = OffsetPagingKey(offset = 20, limit = 10)

        assertEquals(20, key.offset)
        assertEquals(10, key.limit)
    }

    @Test
    fun cursorKeyStoresOpaqueCursor() {
        val key = CursorPagingKey(cursor = mapOf("token" to "abc"), limit = 30)

        assertEquals(mapOf("token" to "abc"), key.cursor)
        assertEquals(30, key.limit)
    }

    @Test
    fun pageStoresItemsKeyAndMetadata() {
        val metadata = PayloadMetadata(totalCount = 3, receivedAtEpochMillis = 123L)
        val page =
            Page(
                items = listOf("a", "b"),
                nextKey = OffsetPagingKey(offset = 2, limit = 2),
                metadata = metadata,
            )

        assertEquals(listOf("a", "b"), page.items)
        assertEquals(OffsetPagingKey(offset = 2, limit = 2), page.nextKey)
        assertEquals(metadata, page.metadata)
    }

    @Test
    fun publicNamesUseGenericTerms() {
        val publicNames =
            listOf(
                    UiState::class.java.simpleName,
                    UiState.Loading::class.java.simpleName,
                    UiState.Success::class.java.simpleName,
                    UiState.Error::class.java.simpleName,
                    OffsetPagingKey::class.java.simpleName,
                    CursorPagingKey::class.java.simpleName,
                    Page::class.java.simpleName,
                    PayloadMetadata::class.java.simpleName,
                )
                .map { it.lowercase() }
        val blockedTerms =
            listOf(
                "po" + "st",
                "com" + "ment",
                "li" + "ke",
                "fol" + "low",
                "fe" + "ed",
                "acti" + "vity",
                "pro" + "file",
            )

        blockedTerms.forEach { term ->
            assertFalse(publicNames.any { name -> name.contains(term) })
        }
    }
}
