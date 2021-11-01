package com.gb.cashback.entity

import java.math.BigDecimal
import javax.persistence.*

@Entity
@Table(name = "purchase_tb")
data class PurchaseEntity(
        @Id
     @GeneratedValue(strategy = GenerationType.AUTO)
     @Column(name = "purchase_id")
        val purchaseId: Long,

        @Column(name = "purchase_code")
        val purchaseCode: String,

        @Column(name = "purchase_value")
        val purchaseValue: Double,

        @Column(name = "purchase_date")
        val purchaseDate: String,

        @Column(name = "purchase_reseller_document")
        val resellerDocument: String,


        )