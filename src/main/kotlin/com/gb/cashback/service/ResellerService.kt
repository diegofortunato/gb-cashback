package com.gb.cashback.service

import com.gb.cashback.dto.ResellerDTO
import com.gb.cashback.entity.ResellerEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest

interface ResellerService {
    fun createReseller(resellerEntity: ResellerEntity): ResellerDTO
    fun findReseller(resellerID: Long): ResellerDTO
    fun findAllReseller(paging: PageRequest): Page<ResellerDTO>
    fun updateReseller(resellerID: Long, resellerEntity: ResellerEntity): ResellerDTO
    fun deleteReseller(resellerID: Long)
}