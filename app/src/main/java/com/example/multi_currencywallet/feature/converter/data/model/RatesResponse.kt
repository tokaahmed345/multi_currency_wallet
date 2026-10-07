package com.example.multi_currencywallet.feature.converter.data.model

import com.example.multi_currencywallet.feature.converter.domain.entity.ExchangeRate

data class RatesResponseDto(
    val amount: Double,
    val base: String,
    val date: String,
    val rates: Map<String, Double>
) {
    fun toEntity(target: String): ExchangeRate? =
        rates[target]?.let { ExchangeRate(base = base, target = target, rate = it, date = date) }

    fun currencyCodes(): List<String> = rates?.keys?.sorted() ?: emptyList()

}