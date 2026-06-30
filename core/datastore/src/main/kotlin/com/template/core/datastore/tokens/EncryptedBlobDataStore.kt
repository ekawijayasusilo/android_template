package com.template.core.datastore.tokens

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import java.io.IOException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

class EncryptedBlobDataStore(private val dataStore: DataStore<Preferences>) {
    val encryptedBlob: Flow<StoredEncryptedBlob?> =
        dataStore.data
            .catch { exception ->
                if (exception is IOException) emit(emptyPreferences()) else throw exception
            }
            .map { preferences ->
                val cipherText = preferences[Keys.cipherText]
                val initializationVector = preferences[Keys.initializationVector]
                if (cipherText.isNullOrBlank() || initializationVector.isNullOrBlank()) {
                    null
                } else {
                    StoredEncryptedBlob(cipherText, initializationVector)
                }
            }

    suspend fun save(encryptedBlob: StoredEncryptedBlob) {
        dataStore.edit { preferences ->
            preferences[Keys.cipherText] = encryptedBlob.cipherText
            preferences[Keys.initializationVector] = encryptedBlob.initializationVector
        }
    }

    suspend fun clear() {
        dataStore.edit { preferences ->
            preferences.remove(Keys.cipherText)
            preferences.remove(Keys.initializationVector)
        }
    }

    private object Keys {
        val cipherText = stringPreferencesKey("encrypted_blob_cipher_text")
        val initializationVector = stringPreferencesKey("encrypted_blob_initialization_vector")
    }
}
