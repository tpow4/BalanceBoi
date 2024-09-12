package com.balance.boi.utils

import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import net.sqlcipher.database.SupportFactory
import java.security.KeyStore
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey

object CipherUtils {
    private fun generateSecretKey() {
        val keyGeneratorParameterBuilder = KeyGenParameterSpec.Builder(
            "LocalSecurityKey",
            KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
        )
            .setBlockModes(KeyProperties.BLOCK_MODE_CBC)
            .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_PKCS7)
            .setUserAuthenticationRequired(true)
            .setInvalidatedByBiometricEnrollment(true)
        val keyGeneratorParameterSpec = keyGeneratorParameterBuilder.build()
        val keyGenerator = KeyGenerator.getInstance(
            KeyProperties.KEY_ALGORITHM_AES,
            "AndroidKeyStore"
        )
        keyGenerator.init(keyGeneratorParameterSpec)
        keyGenerator.generateKey()
    }

    fun getOrCreateSecretKey(): SecretKey {
        val keyStore = KeyStore.getInstance("AndroidKeyStore")
        keyStore.load(null)

        // If the key doesn't exist, generate it
        if(!keyStore.containsAlias("LocalSecurityKey")) {
            generateSecretKey()
        }

        return keyStore.getKey("LocalSecurityKey", null) as SecretKey
    }

    fun getCipher(): Cipher = Cipher.getInstance(
        "${KeyProperties.KEY_ALGORITHM_AES}/${KeyProperties.BLOCK_MODE_CBC}/${KeyProperties.ENCRYPTION_PADDING_PKCS7}"
    )

    fun getFactoryForPassphrase() : SupportFactory {
        val passphrase = getOrCreateSecretKey().encoded
        return SupportFactory(passphrase)
    }
}