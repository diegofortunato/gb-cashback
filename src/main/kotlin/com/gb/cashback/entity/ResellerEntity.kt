package com.gb.cashback.entity

import javax.persistence.*

@Entity
@Table(
        name = "reseller_tb",
        uniqueConstraints =
        [UniqueConstraint(columnNames = ["reseller_document", "reseller_email"])]
)
data class ResellerEntity (

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name = "reseller_id")
        val resellerId: Long,

        @Column(name = "reseller_full_name")
        var resellerFullName: String,

        @Column(name = "reseller_document")
        var resellerDocument: String,

        @Column(name = "reseller_email")
        var resellerEmail: String,

        @Column(name = "reseller_password")
        var resellerPassword: String,
)


