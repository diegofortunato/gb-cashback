package com.gb.cashback.config

import com.gb.cashback.constant.APIConstant
import com.gb.cashback.service.CashbackIntegrationService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitConfig {

    private val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()

    private val retrofitCashback = Retrofit.Builder()
            .baseUrl(APIConstant.BASE_INTEGRATION)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

    fun cashbackService(): CashbackIntegrationService = retrofitCashback.create(CashbackIntegrationService::class.java)
}