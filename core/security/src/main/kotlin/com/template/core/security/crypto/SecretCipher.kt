package com.template.core.security.crypto

data class EncryptedSecret(val cipherText: String, val initializationVector: String)

interface SecretCipher {
    fun encrypt(plainText: ByteArray): EncryptedSecret

    fun decrypt(encryptedSecret: EncryptedSecret): ByteArray
}
