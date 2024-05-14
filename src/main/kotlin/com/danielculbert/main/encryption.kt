package com.danielculbert.main

import javax.crypto.Cipher
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.PBEKeySpec
import javax.crypto.spec.SecretKeySpec
import javax.crypto.spec.IvParameterSpec
import java.security.SecureRandom
import java.util.Base64

//The following code is entirely from GPT4

object CryptoUtils {
    private const val saltLength = 16
    private const val iterationCount = 65536
    private const val keyLength = 256
    private const val algorithm = "AES/CBC/PKCS5Padding"

    fun encrypt(data: String, password: CharArray): String {
        val salt = ByteArray(saltLength).also { SecureRandom().nextBytes(it) }
        val keySpec = PBEKeySpec(password, salt, iterationCount, keyLength)
        val keyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256")
        val keyBytes = keyFactory.generateSecret(keySpec).encoded
        val key = SecretKeySpec(keyBytes, "AES")
        val cipher = Cipher.getInstance(algorithm)
        cipher.init(Cipher.ENCRYPT_MODE, key, IvParameterSpec(ByteArray(16)))

        val encrypted = cipher.doFinal(data.toByteArray(Charsets.UTF_8))
        val saltEncoded = Base64.getEncoder().encodeToString(salt)
        val encryptedEncoded = Base64.getEncoder().encodeToString(encrypted)
        val ivEncoded = Base64.getEncoder().encodeToString(cipher.iv)

        return "$saltEncoded:$ivEncoded:$encryptedEncoded"
    }

    fun decrypt(encryptedData: String, password: CharArray): String {
        val parts = encryptedData.split(":")
        val salt = Base64.getDecoder().decode(parts[0])
        val iv = Base64.getDecoder().decode(parts[1])
        val encrypted = Base64.getDecoder().decode(parts[2])

        val keySpec = PBEKeySpec(password, salt, iterationCount, keyLength)
        val keyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256")
        val keyBytes = keyFactory.generateSecret(keySpec).encoded
        val key = SecretKeySpec(keyBytes, "AES")
        val cipher = Cipher.getInstance(algorithm)
        cipher.init(Cipher.DECRYPT_MODE, key, IvParameterSpec(iv))

        val decryptedBytes = cipher.doFinal(encrypted)
        return String(decryptedBytes, Charsets.UTF_8)
    }
}
