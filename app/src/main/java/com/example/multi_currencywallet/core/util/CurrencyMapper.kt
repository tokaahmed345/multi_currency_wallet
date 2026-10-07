package com.example.multi_currencywallet.core.util


import com.example.multi_currencywallet.core.model.Currency

object CurrencyMapper {

     val flagOverrides = mapOf(
        "EUR" to "🇪🇺"
    )

    fun fromCode(code: String): Currency {
        val name = try {
            java.util.Currency.getInstance(code).displayName
        } catch (e: Exception) {
            code
        }
        return Currency(code = code, name = name, flag = flagFor(code))
    }

     fun flagFor(code: String): String {
        flagOverrides[code]?.let { return it }
        val country = code.take(2)
        if (country.length < 2 || !country.all { it.isLetter() }) return "🏳️"
        val first = Character.codePointAt(country, 0) - 'A'.code + 0x1F1E6
        val second = Character.codePointAt(country, 1) - 'A'.code + 0x1F1E6
        return String(Character.toChars(first)) + String(Character.toChars(second))
    }
}