package com.example.multi_currencywallet.feature.favorites.domain.usecase

import com.example.multi_currencywallet.feature.favorites.domain.entity.FavoritePair
import com.example.multi_currencywallet.feature.favorites.domain.repository.FavoritesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveFavoritesUseCase @Inject constructor(
    private val repository: FavoritesRepository
) {
    operator fun invoke(): Flow<List<FavoritePair>> = repository.observeAll()
}