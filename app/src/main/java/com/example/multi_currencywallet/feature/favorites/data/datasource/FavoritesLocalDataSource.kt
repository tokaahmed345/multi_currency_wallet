package com.example.multi_currencywallet.feature.favorites.data.datasource


import com.example.multi_currencywallet.core.database.FavoriteDao
import com.example.multi_currencywallet.core.database.FavoriteEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface FavoritesLocalDataSource {
    fun observeAll(): Flow<List<FavoriteEntity>>
    fun observeIsSaved(base: String, target: String): Flow<Boolean>
    suspend fun insert(entity: FavoriteEntity)
    suspend fun delete(base: String, target: String)
}

class FavoritesLocalDataSourceImpl @Inject constructor(
    private val dao: FavoriteDao
) : FavoritesLocalDataSource {
    override fun observeAll() = dao.observeAll()
    override fun observeIsSaved(base: String, target: String) = dao.observeIsSaved(base, target)
    override suspend fun insert(entity: FavoriteEntity) = dao.insert(entity)
    override suspend fun delete(base: String, target: String) = dao.delete(base, target)
}