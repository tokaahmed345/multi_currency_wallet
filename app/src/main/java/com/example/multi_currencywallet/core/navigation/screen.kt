package com.example.multi_currencywallet.core.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.SwapHoriz
import androidx.compose.material.icons.rounded.StarOutline
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(
    val route: String,
    val label: String,
    val icon: ImageVector
) {
    data object Converter : Screen("com/example/multi_currencywallet/feature/converter", "Convert", Icons.Rounded.SwapHoriz)
    data object Favorites : Screen("com/example/multi_currencywallet/feature/favorites/ui/FavoritesScreen.kt", "Favorites", Icons.Rounded.StarOutline)
}

val bottomItems = listOf(Screen.Converter, Screen.Favorites)