package com.ibrakor.chainofresponsibility.handlers

import com.ibrakor.chainofresponsibility.PasswordValidator

class LengthPasswordValidator: PasswordValidator {
    override fun validate(password: String): Boolean {
        return password.length >=8
    }
}