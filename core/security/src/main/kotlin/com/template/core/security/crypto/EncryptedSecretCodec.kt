package com.template.core.security.crypto

import java.util.Base64

object EncryptedSecretCodec {
    fun encode(cipherText: ByteArray, initializationVector: ByteArray): EncryptedSecret =
        EncryptedSecret(
            cipherText = Base64.getEncoder().encodeToString(cipherText),
            initializationVector = Base64.getEncoder().encodeToString(initializationVector),
        )

    fun decodeCipherText(encryptedSecret: EncryptedSecret): ByteArray =
        Base64.getDecoder().decode(encryptedSecret.cipherText)

    fun decodeInitializationVector(encryptedSecret: EncryptedSecret): ByteArray =
        Base64.getDecoder().decode(encryptedSecret.initializationVector)
}
