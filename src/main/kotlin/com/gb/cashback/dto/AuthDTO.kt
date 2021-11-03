package com.gb.cashback.dto

import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.constraints.Email
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class AuthDTO(
        @JsonProperty("email")
        @field:NotNull @field:NotEmpty(message = "Email cannot be empty")
        @field:Email(message = "Email should be valid")
        val resellerEmail: String,

        @JsonProperty("senha")
        @field:NotNull @field:NotEmpty(message = "Password cannot be empty")
        val resellerPassword: String,
)
