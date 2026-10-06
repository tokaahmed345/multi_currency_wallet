package com.example.multi_currencywallet.feature.converter.domain.entity


data class ExchangeRate(
    val base: String,
    val target: String,
    val rate: Double,
    val date: String
)