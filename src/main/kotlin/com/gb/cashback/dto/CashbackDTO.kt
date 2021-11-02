package com.gb.cashback.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class CashbackDTO(
    @JsonProperty("credit")
    val credit: Long
)
