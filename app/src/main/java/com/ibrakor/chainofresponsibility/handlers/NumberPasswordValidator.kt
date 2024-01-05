package com.ibrakor.chainofresponsibility.handlers

import com.ibrakor.chainofresponsibility.PasswordValidator

class NumberPasswordValidator: PasswordValidator {
    override fun validate(password: String): Boolean {
        return password.any { it.isDigit() }
    }
}