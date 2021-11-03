package com.gb.cashback.service

import com.gb.cashback.dto.PurchaseDTO
import com.gb.cashback.entity.PurchaseEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest

interface PurchaseService {

    fun createPurchase(purchaseEntity: PurchaseEntity, resellerDocument: String): PurchaseDTO
    fun findAllPurchase(paging: PageRequest): Page<PurchaseDTO>
    fun findPurchaseByReseller(paging: PageRequest, resellerID: Long): Page<PurchaseDTO>
}