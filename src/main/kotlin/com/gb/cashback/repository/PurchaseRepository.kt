package com.gb.cashback.repository

import com.gb.cashback.entity.PurchaseEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PurchaseRepository: JpaRepository <PurchaseEntity, Long>