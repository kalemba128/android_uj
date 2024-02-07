package com.kalemba128.auth.services

import com.kalemba128.auth.R

class EmailValidator {
    private val minLength = 8
    private val maxLength = 128
    private val pattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"

    fun isEmpty(email: String): Boolean {
        return email.replace("\\s+".toRegex(), "").isEmpty()
    }

    fun isTooShort(email: String): Boolean {
        return email.length < minLength
    }

    fun isTooLong(email: String): Boolean {
        return email.length > maxLength
    }

    fun isNotValid(email: String): Boolean {
        return !email.matches(pattern.toRegex())
    }

    fun validate(email: String): Int? {
        if (isEmpty(email)) {
            return R.string.email_empty
        } else if (isTooShort(email)) {
            return R.string.email_short
        } else if (isTooLong(email)) {
            return R.string.email_long
        } else if (isNotValid(email)) {
            return R.string.email_valid
        }
        return null
    }
}