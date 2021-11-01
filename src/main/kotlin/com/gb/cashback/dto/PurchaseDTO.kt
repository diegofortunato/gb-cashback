package com.gb.cashback.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigDecimal
import javax.validation.constraints.NotEmpty

data class PurchaseDTO(
        @JsonProperty("id")
        val purchaseId: Long,

        @JsonProperty("codigo")
        @NotEmpty(message = "Código do produto não pode ser nulo")
        val purchaseCode: String,

        @JsonProperty("valor")
        @NotEmpty(message = "Valor não pode ser nulo")
        val purchaseValue: Double,

        @JsonProperty("data")
        @NotEmpty(message = "Data não pode ser nulo")
        val purchaseDate: String,

        @JsonProperty("documento")
        @NotEmpty(message = "Documento não pode ser nulo")
        val resellerDocument: String,
)
