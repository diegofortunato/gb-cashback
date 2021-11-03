package com.gb.cashback.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class CashbackBodyDTO(
    @JsonProperty("body")
    val body: CashbackDTO
)
