package com.example.multi_currencywallet.feature.converter.data.datasource

import com.example.multi_currencywallet.feature.converter.data.model.RatesResponseDto
import com.example.multi_currencywallet.feature.converter.data.remote.RatesApi
import javax.inject.Inject

interface RatesRemoteDataSource {
    suspend fun getRates(base: String, target: String): RatesResponseDto
    suspend fun getCurrencies(): Map<String, String>
}

class RatesRemoteDataSourceImpl @Inject constructor(
    private val api: RatesApi
) : RatesRemoteDataSource {

    override suspend fun getRates(base: String, target: String): RatesResponseDto =
        api.getLatest(base = base, symbols = target)

    override suspend fun getCurrencies(): Map<String, String> =
        api.getCurrencies()
}