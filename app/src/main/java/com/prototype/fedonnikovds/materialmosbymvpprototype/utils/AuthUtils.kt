package com.prototype.fedonnikovds.materialmosbymvpprototype.utils

import java.math.BigInteger
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

class AuthUtils {

    fun getPasswordHash(password: String, withSalt: Boolean): String? {
        try {
            val messageDigest = MessageDigest.getInstance("MD5")
            val passwordWithSalt = if (withSalt) {
                password + salt
            } else {
                password
            }

            messageDigest.update(passwordWithSalt.toByteArray())

            return String.format("%1$32s", BigInteger(1, messageDigest.digest()).toString(16)).replace(' ', '0')
        } catch (e: NoSuchAlgorithmException) {
                       e.printStackTrace()
        }
        return null
    }

    companion object {

        private const val salt = "4f8202ccd76210b47b40627c621daa56"

    }
}