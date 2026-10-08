package com.example.multi_currencywallet.feature.favorites.domain.repository


import com.example.multi_currencywallet.feature.favorites.domain.entity.FavoritePair
import kotlinx.coroutines.flow.Flow

interface FavoritesRepository {
    fun observeAll(): Flow<List<FavoritePair>>
    fun observeIsFavorite(base: String, target: String): Flow<Boolean>
    suspend fun add(pair: FavoritePair)
    suspend fun remove(base: String, target: String)
}