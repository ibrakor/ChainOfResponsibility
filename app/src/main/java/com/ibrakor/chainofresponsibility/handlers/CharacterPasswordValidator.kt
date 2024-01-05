package com.ibrakor.chainofresponsibility.handlers

import com.ibrakor.chainofresponsibility.PasswordValidator

class CharacterPasswordValidator: PasswordValidator {
    override fun validate(password: String): Boolean {
        return "@" in password
    }
}