package com.gb.cashback.repository

import com.gb.cashback.entity.PurchaseEntity
import com.gb.cashback.entity.ResellerEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface PurchaseRepository: JpaRepository <PurchaseEntity, Long> {
    fun findByPurchaseCode(purchaseCode: String): Optional<PurchaseEntity>
    fun findAllByResellerEntity(pageable: Pageable, resellerEntity: ResellerEntity): Page<PurchaseEntity>
}