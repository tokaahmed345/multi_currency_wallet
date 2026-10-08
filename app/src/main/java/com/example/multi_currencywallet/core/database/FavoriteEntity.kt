package com.example.multi_currencywallet.core.database


import androidx.room.Entity

@Entity(tableName = "favorites", primaryKeys = ["base", "target"])
data class FavoriteEntity(
    val base: String,
    val target: String,
    val rate: Double,
    val updatedAt: Long
)