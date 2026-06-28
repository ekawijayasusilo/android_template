package com.template.core.security.crypto

import org.junit.Assert.assertArrayEquals
import org.junit.Assert.assertEquals
import org.junit.Test

class EncryptedSecretCodecTest {
    @Test
    fun encodesAndDecodesBase64Payloads() {
        val cipherText = byteArrayOf(1, 2, 3)
        val initializationVector = byteArrayOf(4, 5, 6)

        val encryptedSecret = EncryptedSecretCodec.encode(cipherText, initializationVector)

        assertEquals("AQID", encryptedSecret.cipherText)
        assertEquals("BAUG", encryptedSecret.initializationVector)
        assertArrayEquals(cipherText, EncryptedSecretCodec.decodeCipherText(encryptedSecret))
        assertArrayEquals(
            initializationVector,
            EncryptedSecretCodec.decodeInitializationVector(encryptedSecret),
        )
    }
}
