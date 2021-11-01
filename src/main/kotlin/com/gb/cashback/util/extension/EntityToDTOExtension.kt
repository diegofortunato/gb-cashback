package com.gb.cashback.util.extension

import com.gb.cashback.dto.ResellerDTO
import com.gb.cashback.entity.ResellerEntity

object EntityToDTOExtension {

    fun ResellerEntity.toDTO() = ResellerDTO(
            this.resellerId,
            this.resellerFullName,
            this.resellerDocument,
            this.resellerEmail,
            this.resellerPassword
    )
}