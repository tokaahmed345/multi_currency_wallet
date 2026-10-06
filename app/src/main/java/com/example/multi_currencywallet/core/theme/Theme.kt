package com.example.multi_currencywallet.core.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
private val LightColors = lightColorScheme(
    primary = Violet,
    secondary = Mint,
    background = BgLight,
    surface = SurfaceLight,
    onBackground = TextLight,
    onSurface = TextLight,
    onSurfaceVariant = MutedLight,
)

private val DarkColors = darkColorScheme(
    primary = VioletDark,
    secondary = MintDark,
    background = BgDark,
    surface = SurfaceDark,
    onBackground = TextDark,
    onSurface = TextDark,
    onSurfaceVariant = MutedDark,
)
@Composable
fun MultiCurrencyWalletTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColors
        else -> LightColors
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}