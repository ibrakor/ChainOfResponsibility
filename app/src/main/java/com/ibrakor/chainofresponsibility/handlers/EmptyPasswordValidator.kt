package com.ibrakor.chainofresponsibility.handlers

import com.ibrakor.chainofresponsibility.PasswordValidator

class EmptyPasswordValidator: PasswordValidator {
    override fun validate(password: String): Boolean {
        return password.isNotEmpty()
    }
}