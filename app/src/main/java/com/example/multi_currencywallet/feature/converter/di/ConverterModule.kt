package com.example.multi_currencywallet.feature.converter.di


import com.example.multi_currencywallet.feature.converter.data.datasource.RatesRemoteDataSource
import com.example.multi_currencywallet.feature.converter.data.datasource.RatesRemoteDataSourceImpl
import com.example.multi_currencywallet.feature.converter.data.RatesRepositoryImpl.RatesRepositoryImpl
import com.example.multi_currencywallet.feature.converter.domain.repository.RatesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ConverterModule {

    @Binds
    @Singleton
    abstract fun bindRemoteDataSource(impl: RatesRemoteDataSourceImpl): RatesRemoteDataSource

    @Binds
    @Singleton
    abstract fun bindRatesRepository(impl: RatesRepositoryImpl): RatesRepository
}