package com.gb.cashback.controller

import com.gb.cashback.constant.APIConstant
import com.gb.cashback.controller.request.Request
import com.gb.cashback.controller.response.Response
import com.gb.cashback.dto.AuthDTO
import com.gb.cashback.service.AuthService
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping(value = [APIConstant.BASE_API])
class AuthController(private val authService: AuthService) {

    private val log = LoggerFactory.getLogger(javaClass)

    @PostMapping(APIConstant.SERVICE_POST_AUTH)
    fun authReseller(@Valid @RequestBody authRequest: Request<AuthDTO>): ResponseEntity<Response<AuthDTO>> {
        log.info("POST ${APIConstant.SERVICE_POST_AUTH} for reseller {}", authRequest.request.resellerEmail)

        authService.login(authRequest.request.resellerEmail, authRequest.request.resellerPassword)
        return ResponseEntity.ok().build()
    }
}