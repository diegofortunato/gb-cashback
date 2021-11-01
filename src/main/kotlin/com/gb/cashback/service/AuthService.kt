package com.gb.cashback.service

interface AuthService {
    fun login(resellerEmail: String, resellerPassword: String)
}