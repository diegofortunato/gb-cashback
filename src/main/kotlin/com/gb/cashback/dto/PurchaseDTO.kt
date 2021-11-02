package com.gb.cashback.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigDecimal
import javax.validation.constraints.NotEmpty

data class PurchaseDTO(
        @JsonProperty("id")
        val purchaseId: Long,

        @JsonProperty("code")
        @NotEmpty(message = "Code cannot be null")
        val purchaseCode: String,

        @JsonProperty("value")
        @NotEmpty(message = "Value cannot be null")
        val purchaseValue: Double,

        @JsonProperty("date")
        @NotEmpty(message = "Date cannot be null")
        val purchaseDate: String,

        @JsonProperty("document")
        @NotEmpty(message = "Document cannot be null")
        val resellerDocument: String,
)
