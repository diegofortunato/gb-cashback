package com.gb.cashback.repository

import com.gb.cashback.entity.DocumentExceptionEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface DocumentExceptionRepository : JpaRepository<DocumentExceptionEntity, Long>