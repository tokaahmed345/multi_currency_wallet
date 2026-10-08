
package com.example.multi_currencywallet.feature.converter.ui

import com.example.multi_currencywallet.core.error.Failure
import com.example.multi_currencywallet.core.model.Currency
import com.example.multi_currencywallet.core.util.CurrencyMapper

data class ConverterUiState(
    val amount: String = "1000",
    val from: Currency = CurrencyMapper.fromCode("USD"),
    val to: Currency = CurrencyMapper.fromCode("EUR"),
    val currencies: List<Currency> = emptyList(),
    val rate: Double? = null,
    val result: Double? = null,
    val isLoading: Boolean = false,
    val isSaved: Boolean = false,
    val failure: Failure? = null
)