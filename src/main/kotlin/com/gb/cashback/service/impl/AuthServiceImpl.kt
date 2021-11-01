package com.gb.cashback.service.impl

import com.gb.cashback.exception.AuthException
import com.gb.cashback.repository.ResellerRepository
import com.gb.cashback.service.AuthService
import com.gb.cashback.util.APPUtil
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class AuthServiceImpl(private val resellerRepository: ResellerRepository) : AuthService {

    private val log = LoggerFactory.getLogger(javaClass)

    override fun login(resellerEmail: String, resellerPassword: String) {
        log.info("Find Login service. resellerEmaail={}", resellerEmail)

        resellerRepository.findByResellerEmailAndResellerPassword(resellerEmail, resellerPassword)
                .orElseThrow { AuthException("Login error") }
    }
}