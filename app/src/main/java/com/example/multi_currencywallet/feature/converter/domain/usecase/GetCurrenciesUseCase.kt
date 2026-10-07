package com.example.multi_currencywallet.feature.converter.domain.usecase


import com.example.multi_currencywallet.core.error.Failure
import com.example.multi_currencywallet.core.model.Currency
import com.example.multi_currencywallet.core.util.Either
import com.example.multi_currencywallet.feature.converter.domain.repository.RatesRepository
import javax.inject.Inject

class GetCurrenciesUseCase @Inject constructor(
    private val repository: RatesRepository
) {
    suspend operator fun invoke(): Either<Failure, List<Currency>> =
        repository.getCurrencies()
}