package com.example.multi_currencywallet.feature.favorites.di


import com.example.multi_currencywallet.feature.favorites.data.datasource.FavoritesLocalDataSource
import com.example.multi_currencywallet.feature.favorites.data.datasource.FavoritesLocalDataSourceImpl
import com.example.multi_currencywallet.feature.favorites.data.repository.FavoritesRepositoryImpl
import com.example.multi_currencywallet.feature.favorites.domain.repository.FavoritesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class FavoritesModule {

    @Binds
    @Singleton
    abstract fun bindLocalDataSource(impl: FavoritesLocalDataSourceImpl): FavoritesLocalDataSource

    @Binds
    @Singleton
    abstract fun bindFavoritesRepository(impl: FavoritesRepositoryImpl): FavoritesRepository
}