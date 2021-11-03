package com.gb.cashback.service.impl

import com.gb.cashback.config.AppProperties
import com.gb.cashback.config.RetrofitConfig
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CashbackIntegrationServiceImpl {

    @Autowired
    lateinit var appProperties: AppProperties

    fun getCashbackIntegration(resellerDocument: String): Long {
        val call = RetrofitConfig(appProperties).cashbackService().getCashback(resellerDocument)
        return call.execute().body()!!.body.credit
    }
}