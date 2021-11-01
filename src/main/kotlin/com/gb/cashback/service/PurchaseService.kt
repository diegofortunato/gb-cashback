package com.gb.cashback.service

import com.gb.cashback.dto.PurchaseDTO
import com.gb.cashback.entity.PurchaseEntity

interface PurchaseService {

    fun createPurchase(purchaseEntity: PurchaseEntity): PurchaseDTO
    fun findPurchases(): List<PurchaseDTO>
}