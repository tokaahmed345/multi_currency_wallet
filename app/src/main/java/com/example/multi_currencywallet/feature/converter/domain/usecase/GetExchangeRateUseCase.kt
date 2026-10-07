package com.example.multi_currencywallet.feature.converter.domain.usecase



import com.example.multi_currencywallet.core.error.Failure
import com.example.multi_currencywallet.core.util.Either
import com.example.multi_currencywallet.feature.converter.domain.entity.ExchangeRate
import com.example.multi_currencywallet.feature.converter.domain.repository.RatesRepository
import javax.inject.Inject

class GetExchangeRateUseCase @Inject constructor(
    private val repository: RatesRepository
) {
    suspend operator fun invoke(base: String, target: String): Either<Failure, ExchangeRate> =
        repository.getRate(base, target)
}