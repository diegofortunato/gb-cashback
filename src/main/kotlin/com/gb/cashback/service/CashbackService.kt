package com.gb.cashback.service

import com.gb.cashback.dto.CashbackDTO

interface CashbackService {
    fun findCashback(resellerDocument: String): CashbackDTO
}