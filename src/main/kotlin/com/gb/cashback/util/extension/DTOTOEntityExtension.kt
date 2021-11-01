package com.gb.cashback.util.extension

import com.gb.cashback.dto.PurchaseDTO
import com.gb.cashback.dto.ResellerDTO
import com.gb.cashback.entity.PurchaseEntity
import com.gb.cashback.entity.ResellerEntity

object DTOTOEntityExtension {

    fun ResellerDTO.toEntity() = ResellerEntity(
        this.resellerId,
        this.resellerFullName,
        this.resellerDocument,
        this.resellerEmail,
        this.resellerPassword
    )

    fun PurchaseDTO.toEntity() = PurchaseEntity(
        this.purchaseId,
        this.purchaseCode,
        this.purchaseValue,
        this.purchaseDate,
        this.resellerDocument
    )
}