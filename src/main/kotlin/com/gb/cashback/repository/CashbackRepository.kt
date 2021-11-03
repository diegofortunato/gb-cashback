package com.gb.cashback.repository

import com.gb.cashback.entity.CashbackEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CashbackRepository : JpaRepository<CashbackEntity, Long>