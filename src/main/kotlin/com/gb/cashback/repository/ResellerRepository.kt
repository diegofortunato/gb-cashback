package com.gb.cashback.repository

import com.gb.cashback.entity.ResellerEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ResellerRepository: JpaRepository<ResellerEntity, Long> {

    fun findByResellerEmailOrResellerDocument(resellerEmail: String, resellerDocument: String): Optional<ResellerEntity>
    fun findByResellerEmailAndResellerPassword(resellerEmail: String, resellerPassword: String): Optional<ResellerEntity>
    fun findByResellerEmail(email: String): Optional<ResellerEntity>
    fun findByResellerDocument(document: String): Optional<ResellerEntity>
}