package com.gb.cashback.dto

import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.constraints.NotEmpty

data class AuthDTO(
        @JsonProperty("email")
        @NotEmpty(message = "Email cannot be null")
        val resellerEmail: String,

        @JsonProperty("senha")
        @NotEmpty(message = "Password cannot be null")
        val resellerPassword: String,
)
