package com.ibrakor.chainofresponsibility

interface PasswordValidator {

    fun validate(password: String): Boolean
}