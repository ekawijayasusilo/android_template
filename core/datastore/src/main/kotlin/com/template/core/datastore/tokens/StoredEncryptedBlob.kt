package com.template.core.datastore.tokens

data class StoredEncryptedBlob(val cipherText: String, val initializationVector: String) {
    init {
        require(cipherText.isNotBlank()) { "Encrypted blob cipher text must not be blank" }
        require(initializationVector.isNotBlank()) {
            "Encrypted blob initialization vector must not be blank"
        }
    }
}
