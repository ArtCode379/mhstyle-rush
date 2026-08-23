package mhstore.apparel.mhstylerush.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val BrandColors =
    lightColorScheme(
        primary = BrandPrimary,
        onPrimary = BrandSurface,
        secondary = BrandAccent,
        onSecondary = BrandSurface,
        background = BrandBackground,
        onBackground = BrandInk,
        surface = BrandSurface,
        onSurface = BrandInk,
        surfaceVariant = BrandBackground,
        onSurfaceVariant = BrandMuted,
        outline = BrandBorder,
    )

@Composable
fun ProductAppJNVNJTheme(
    darkTheme: Boolean = false,
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit,
) {
    MaterialTheme(colorScheme = BrandColors, typography = AppTypography, content = content)
}
