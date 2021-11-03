package com.gb.cashback.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigDecimal
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class PurchaseDTO(
        @JsonProperty("id")
        val purchaseId: Long,

        @JsonProperty("code")
        @field:NotNull @field:NotEmpty(message = "Code cannot be empty")
        val purchaseCode: String,

        @JsonProperty("value")
        @field:NotNull(message = "Value cannot be null")
        val purchaseValue: BigDecimal,

        @JsonProperty("date")
        @field:NotNull @field:NotEmpty(message = "Date cannot be empty")
        val purchaseDate: String,

        @JsonProperty("document")
        @field:Size(min = 14, message = "Document should have at least 14 characters. EX: xxx.xxx.xxx-xx")
        @field:NotNull @field:NotEmpty(message = "Document cannot be empty")
        val resellerDocument: String,
)
