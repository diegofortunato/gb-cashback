package com.gb.cashback.entity

import com.gb.cashback.constant.StatusPurchaseEnum
import java.math.BigDecimal
import java.time.LocalDate
import javax.persistence.Entity
import javax.persistence.Table
import javax.persistence.Id
import javax.persistence.GeneratedValue
import javax.persistence.Column
import javax.persistence.GenerationType
import javax.persistence.Enumerated
import javax.persistence.EnumType
import javax.persistence.ManyToOne
import javax.persistence.JoinColumn

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

    @Column(name = "purchase_status")
    @Enumerated(EnumType.STRING)
    var purchaseStatus: StatusPurchaseEnum?,

    @Column(name = "purchase_value_cashback")
    var purchaseValueCashback: BigDecimal?,

    @Column(name = "purchase_percentage_cashback")
    var purchasePercentage: Int?,

    @ManyToOne
    @JoinColumn(name="purchase_reseller")
    var resellerEntity: ResellerEntity?,
)