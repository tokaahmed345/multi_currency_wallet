package com.example.multi_currencywallet.feature.converter.domain.repository


import com.example.multi_currencywallet.core.error.Failure
import com.example.multi_currencywallet.core.util.Either
import com.example.multi_currencywallet.feature.converter.domain.entity.ExchangeRate

interface RatesRepository {
    suspend fun getRate(base: String, target: String): Either<Failure, ExchangeRate>
}