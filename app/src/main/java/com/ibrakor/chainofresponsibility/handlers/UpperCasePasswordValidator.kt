package com.ibrakor.chainofresponsibility.handlers

import com.ibrakor.chainofresponsibility.PasswordValidator

class UpperCasePasswordValidator: PasswordValidator {
    override fun validate(password: String): Boolean {
        return password.any {it.isUpperCase()}
    }
}