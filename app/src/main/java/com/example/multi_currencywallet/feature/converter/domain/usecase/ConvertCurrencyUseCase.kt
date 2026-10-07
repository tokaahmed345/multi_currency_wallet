package com.example.multi_currencywallet.feature.converter.domain.usecase


import javax.inject.Inject

class ConvertCurrencyUseCase @Inject constructor() {
    operator fun invoke(amount: Double, rate: Double): Double = amount * rate
}