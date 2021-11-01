package com.gb.cashback.dto

import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.constraints.NotEmpty

data class AuthDTO(
        @JsonProperty("email")
        @NotEmpty(message = "Email não pode ser nulo")
        val resellerEmail: String,

        @JsonProperty("senha")
        @NotEmpty(message = "Senha não pode ser nula")
        val resellerPassword: String,
)
