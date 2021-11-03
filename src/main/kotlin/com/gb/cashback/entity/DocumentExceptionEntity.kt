package com.gb.cashback.entity

import javax.persistence.Entity
import javax.persistence.Table
import javax.persistence.Id
import javax.persistence.GeneratedValue
import javax.persistence.Column
import javax.persistence.GenerationType

@Entity
@Table(name = "document_exception_tb")
data class DocumentExceptionEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    val purchaseId: Long,

    @Column(name = "document")
    val document: String,
)