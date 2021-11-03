package com.gb.cashback.controller

import com.gb.cashback.constant.APIConstant
import com.gb.cashback.controller.response.Response
import com.gb.cashback.dto.CashbackDTO
import com.gb.cashback.service.CashbackService
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = [APIConstant.BASE_API])
class CashbackController(private val cashbackService: CashbackService) {

    private val log = LoggerFactory.getLogger(javaClass)

    @GetMapping(APIConstant.SERVICE_GET_CASHBACK)
    fun findCashback(@PathVariable("cpf") resellerDocument: String): ResponseEntity<Response<CashbackDTO>> {
        log.info("GET ${APIConstant.SERVICE_GET_CASHBACK}")

        val cashback = cashbackService.findCashback(resellerDocument)
        return ResponseEntity.ok(Response(data = cashback))
    }
}