package com.gb.cashback.controller.response

data class ResponsePagination<T>(
        var data: T,
        var totalPages: Int,
        var currentPage: Int,
        var totalItems: Long
)