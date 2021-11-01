package com.gb.cashback.util.extension

import com.gb.cashback.dto.ResellerDTO
import com.gb.cashback.entity.ResellerEntity

object DTOTOEntityExtension {

    fun ResellerDTO.toEntity() = ResellerEntity(
            this.resellerId,
            this.resellerFullName,
            this.resellerDocument,
            this.resellerEmail,
            this.resellerPassword
    )
}