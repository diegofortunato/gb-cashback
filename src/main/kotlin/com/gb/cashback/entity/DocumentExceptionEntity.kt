package com.gb.cashback.entity

import javax.persistence.*

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
