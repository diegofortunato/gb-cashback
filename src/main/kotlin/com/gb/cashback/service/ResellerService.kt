package com.gb.cashback.service

import com.gb.cashback.dto.ResellerDTO
import com.gb.cashback.entity.ResellerEntity

interface ResellerService {
    fun createReseller(resellerEntity: ResellerEntity): ResellerDTO
    fun findReseller(resellerID: Long): ResellerDTO
    fun updateReseller(resellerID: Long, resellerEntity: ResellerEntity): ResellerDTO
    fun deleteReseller(resellerID: Long)
}