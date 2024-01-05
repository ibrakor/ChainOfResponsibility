package com.ibrakor.chainofresponsibility.handlers

import com.ibrakor.chainofresponsibility.PasswordValidator

class LowerCasePasswordValidator : PasswordValidator{
    override fun validate(password: String): Boolean {
        return password.any{ it.isLowerCase()}
    }
}