package com.template.core.datastore.tokens

import com.template.core.datastore.FakePreferenceDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class EncryptedBlobDataStoreTest {
    @Test
    fun savesAndClearsEncryptedBlob() = runTest {
        val encryptedBlobDataStore = EncryptedBlobDataStore(FakePreferenceDataStore())

        assertNull(encryptedBlobDataStore.encryptedBlob.first())

        val encryptedBlob = StoredEncryptedBlob(cipherText = "cipher", initializationVector = "iv")
        encryptedBlobDataStore.save(encryptedBlob)

        assertEquals(encryptedBlob, encryptedBlobDataStore.encryptedBlob.first())

        encryptedBlobDataStore.clear()

        assertNull(encryptedBlobDataStore.encryptedBlob.first())
    }
}
