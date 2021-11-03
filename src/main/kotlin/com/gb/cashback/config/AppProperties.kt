package com.gb.cashback.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class AppProperties {

    @Value("\${access.token.integration}")
    lateinit var accessToken: String
}