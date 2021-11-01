package com.gb.cashback.dto

import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.constraints.NotEmpty

data class ResellerDTO(
        @JsonProperty("id")
        val resellerId: Long,

        @JsonProperty("nome")
        @NotEmpty(message = "Nome completo não pode ser nulo")
        val resellerFullName: String,

        @JsonProperty("documento")
        @NotEmpty(message = "CPF não pode ser nulo")
        val resellerDocument: String,

        @JsonProperty("email")
        @NotEmpty(message = "Email não pode ser nulo")
        val resellerEmail: String,

        @JsonProperty("senha")
        @NotEmpty(message = "Senha não pode ser nula")
        val resellerPassword: String,
)
