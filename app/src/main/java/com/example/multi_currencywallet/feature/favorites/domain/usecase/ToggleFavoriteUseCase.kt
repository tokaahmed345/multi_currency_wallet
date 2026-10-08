package com.example.multi_currencywallet.feature.favorites.domain.usecase


import com.example.multi_currencywallet.feature.favorites.domain.entity.FavoritePair
import com.example.multi_currencywallet.feature.favorites.domain.repository.FavoritesRepository
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class ToggleFavoriteUseCase @Inject constructor(
    private val repository: FavoritesRepository
) {
    suspend operator fun invoke(base: String, target: String, rate: Double) {
        val isSaved = repository.observeIsFavorite(base, target).first()
        if (isSaved) {
            repository.remove(base, target)
        } else {
            repository.add(FavoritePair(base, target, rate, System.currentTimeMillis()))
        }
    }
}