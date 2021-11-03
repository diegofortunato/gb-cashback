package com.gb.cashback.entity

import java.math.BigDecimal
import javax.persistence.Entity
import javax.persistence.Table
import javax.persistence.Id
import javax.persistence.GeneratedValue
import javax.persistence.Column
import javax.persistence.GenerationType

@Entity
@Table(name = "cashback_tb")
data class CashbackEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    val purchaseId: Long,

    @Column(name = "min_value_cashback")
    val minValueCashback: BigDecimal,

    @Column(name = "max_value_cashback")
    val maxValueCashback: BigDecimal,

    @Column(name = "percentage_cashback")
    val percentage: Int,
)