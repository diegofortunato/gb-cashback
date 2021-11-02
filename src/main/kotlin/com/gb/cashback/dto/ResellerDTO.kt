package com.gb.cashback.dto

import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.constraints.Email
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class ResellerDTO(
        @JsonProperty("id")
        val resellerId: Long,

        @JsonProperty("name")
        @field:NotNull @field:NotEmpty(message = "Full name cannot be empty")
        val resellerFullName: String,

        @JsonProperty("document")
        @field:Size(min = 14, message = "Document should have at least 14 characters. EX: xxx.xxx.xxx-xx")
        @field:NotNull @field:NotEmpty(message = "Document cannot be empty")
        val resellerDocument: String,

        @JsonProperty("email")
        @field:NotNull @field:NotEmpty(message = "Email cannot be empty")
        @field:Email(message = "Email should be valid")
        val resellerEmail: String,

        @JsonProperty("password", access = JsonProperty.Access.WRITE_ONLY)
        @field:Size(min = 8, message = "Password should have at least 8 characters")
        @field:NotNull @field:NotEmpty(message = "Password cannot be empty")
        val resellerPassword: String,
)
