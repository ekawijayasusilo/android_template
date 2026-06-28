package com.template.core.datastore.tokens

import org.junit.Assert.assertEquals
import org.junit.Assert.fail
import org.junit.Test

class StoredEncryptedBlobTest {
    @Test
    fun storesEncryptedParts() {
        val blob = StoredEncryptedBlob(cipherText = "cipher", initializationVector = "iv")

        assertEquals("cipher", blob.cipherText)
        assertEquals("iv", blob.initializationVector)
    }

    @Test
    fun rejectsBlankCipherText() {
        try {
            StoredEncryptedBlob(cipherText = "", initializationVector = "iv")
            fail("Expected blank cipher text to fail")
        } catch (exception: IllegalArgumentException) {
            assertEquals("Encrypted blob cipher text must not be blank", exception.message)
        }
    }
}
