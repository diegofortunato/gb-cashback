package com.gb.cashback.service.impl

import com.gb.cashback.dto.CashbackDTO
import com.gb.cashback.service.CashbackService
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class CashbackServiceImpl(
        private val cashbackIntegrationService: CashbackIntegrationServiceImpl
): CashbackService {

    private val log = LoggerFactory.getLogger(javaClass)

    override fun findCashback(): CashbackDTO {
        log.info("Find Cashback")

        val cashback = cashbackIntegrationService.getCashbackIntegration()
        return CashbackDTO(cashback)
    }
}