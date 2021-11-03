package com.gb.cashback.util.extension

import com.gb.cashback.dto.PurchaseDTO
import com.gb.cashback.dto.ResellerDTO
import com.gb.cashback.entity.PurchaseEntity
import com.gb.cashback.entity.ResellerEntity

object EntityToDTOExtension {

    fun ResellerEntity.toDTO() = ResellerDTO(
        this.resellerId,
        this.resellerFullName,
        this.resellerDocument,
        this.resellerEmail,
        this.resellerPassword
    )

    fun PurchaseEntity.toDTO() = PurchaseDTO(
        this.purchaseId,
        this.purchaseCode,
        this.purchaseValue,
        this.purchaseDate.toString(),
        this.purchaseStatus!!.value,
        this.purchaseValueCashback,
        this.purchasePercentage,
        this.resellerEntity!!.resellerDocument
    )
}