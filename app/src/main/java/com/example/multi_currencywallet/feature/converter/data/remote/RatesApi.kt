package com.example.multi_currencywallet.feature.converter.data.remote


import com.example.multi_currencywallet.feature.converter.data.model.RatesResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface RatesApi {
    @GET("latest")
    suspend fun getLatest(
        @Query("base") base: String,
        @Query("symbols") symbols: String
    ): RatesResponseDto

    @GET("currencies")
    suspend fun getCurrencies(): Map<String, String>
}