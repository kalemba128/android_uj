package com.kalemba128.auth.services

import com.kalemba128.auth.R

class PasswordValidator {
    private val minLength = 8
    private val maxLength = 128
    private val minDigitsCount = 1
    private val specialCharacters = listOf('#', '@', '!', '$', '%', '^', '&', '*', '(', ')')

    fun isEmpty(password: String): Boolean {
        return password.replace("\\s+".toRegex(), "").isEmpty()
    }

    fun isTooShort(password: String): Boolean {
        return password.length < minLength
    }

    fun isTooLong(password: String): Boolean {
        return password.length > maxLength
    }

    fun hasEnoughDigits(password: String): Boolean {
        return password.count(Char::isDigit) >= minDigitsCount
    }

    fun isMixedCase(password: String): Boolean {
        return password.any(Char::isLowerCase) && password.any(Char::isUpperCase)
    }

    fun hasSpecialCharacters(password: String): Boolean {
        return password.any { it in specialCharacters }
    }

    fun validate(password: String): Int? {
        if (isEmpty(password)) {
            return R.string.password_empty
        } else if (isTooShort(password)) {
            return R.string.password_short
        } else if (isTooLong(password)) {
            return R.string.password_long
        } else if (!hasEnoughDigits(password)) {
            return R.string.password_digits
        } else if (!isMixedCase(password)) {
            return R.string.password_mixed
        } else if (!hasSpecialCharacters(password)) {
            return R.string.password_special
        }
        return null
    }
}