package com.ibrakor.chainofresponsibility

import com.ibrakor.chainofresponsibility.PasswordValidator

class PasswordValidatorChain(handlers: List<PasswordValidator>) : PasswordValidator{
    private val validators = handlers

    override fun validate(password: String): Boolean {
        for (handler  in validators){
            if (!handler.validate(password)){
                return false
            }
        }
        return true
    }
}