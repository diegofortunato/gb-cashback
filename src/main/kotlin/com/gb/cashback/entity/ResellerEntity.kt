package com.gb.cashback.entity

import javax.persistence.Entity
import javax.persistence.Table
import javax.persistence.UniqueConstraint
import javax.persistence.Id
import javax.persistence.GeneratedValue
import javax.persistence.Column
import javax.persistence.GenerationType

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