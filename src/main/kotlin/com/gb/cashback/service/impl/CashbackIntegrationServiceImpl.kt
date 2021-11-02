package com.gb.cashback.service.impl

import com.gb.cashback.config.RetrofitConfig
import org.springframework.stereotype.Service

@Service
class CashbackIntegrationServiceImpl {

    fun getCashbackIntegration(): Long {
        val call = RetrofitConfig().cashbackService().getCashback()
        return call.execute().body()!!.credit
    }
}