package com.example.multi_currencywallet.feature.favorites.domain.entity


data class FavoritePair(
    val base: String,
    val target: String,
    val rate: Double,
    val updatedAt: Long
) {
    val id: String get() = "$base$target"
}