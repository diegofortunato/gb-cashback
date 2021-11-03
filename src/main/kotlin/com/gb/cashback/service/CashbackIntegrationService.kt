package com.gb.cashback.service

import com.gb.cashback.constant.APIConstant
import com.gb.cashback.dto.CashbackBodyDTO
import com.gb.cashback.dto.CashbackDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CashbackIntegrationService {

    @GET(APIConstant.SERVICE_GET_CASHBACK_INTEGRATION)
    fun getCashback(@Query("cpf") resellerDocument: String): Call<CashbackBodyDTO>
}
