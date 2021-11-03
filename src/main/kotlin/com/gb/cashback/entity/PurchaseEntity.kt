package com.gb.cashback.entity

import java.math.BigDecimal
import java.time.LocalDate
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
    val purchaseValue: BigDecimal,

    @Column(name = "purchase_date")
    val purchaseDate: LocalDate,

    @ManyToOne
    @JoinColumn(name="purchase_reseller")
    var resellerEntity: ResellerEntity?,
)