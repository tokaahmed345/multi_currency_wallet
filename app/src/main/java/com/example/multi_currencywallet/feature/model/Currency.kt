package com.example.multi_currencywallet.feature.model

data class Currency(
    val code: String,
    val name: String,
    val flag: String
)

val fakeCurrencies = listOf(
    Currency("USD", "US Dollar", "🇺🇸"),
    Currency("EGP", "Egyptian Pound", "🇪🇬"),
    Currency("EUR", "Euro", "🇪🇺"),
    Currency("GBP", "British Pound", "🇬🇧"),
    Currency("SAR", "Saudi Riyal", "🇸🇦"),
    Currency("AED", "UAE Dirham", "🇦🇪"),
    Currency("JPY", "Japanese Yen", "🇯🇵")
)