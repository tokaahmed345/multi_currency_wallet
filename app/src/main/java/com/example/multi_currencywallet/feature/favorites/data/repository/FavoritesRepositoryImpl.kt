package com.example.multi_currencywallet.feature.favorites.data.repository

import com.example.multi_currencywallet.core.database.FavoriteEntity
import com.example.multi_currencywallet.feature.favorites.data.datasource.FavoritesLocalDataSource
import com.example.multi_currencywallet.feature.favorites.domain.entity.FavoritePair
import com.example.multi_currencywallet.feature.favorites.domain.repository.FavoritesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FavoritesRepositoryImpl @Inject constructor(
    private val local: FavoritesLocalDataSource
) : FavoritesRepository {

    override fun observeAll(): Flow<List<FavoritePair>> =
        local.observeAll().map { list -> list.map { it.toDomain() } }

    override fun observeIsFavorite(base: String, target: String): Flow<Boolean> =
        local.observeIsSaved(base, target)

    override suspend fun add(pair: FavoritePair) =
        local.insert(FavoriteEntity(pair.base, pair.target, pair.rate, pair.updatedAt))

    override suspend fun remove(base: String, target: String) =
        local.delete(base, target)

    private fun FavoriteEntity.toDomain() =
        FavoritePair(base = base, target = target, rate = rate, updatedAt = updatedAt)
}