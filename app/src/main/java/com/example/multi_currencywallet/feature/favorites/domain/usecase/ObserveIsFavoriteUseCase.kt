package com.example.multi_currencywallet.feature.favorites.domain.usecase


import com.example.multi_currencywallet.feature.favorites.domain.repository.FavoritesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveIsFavoriteUseCase @Inject constructor(
    private val repository: FavoritesRepository
) {
    operator fun invoke(base: String, target: String): Flow<Boolean> =
        repository.observeIsFavorite(base, target)
}