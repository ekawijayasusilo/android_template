package com.template.core.designsystem.theme

import android.annotation.SuppressLint
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

private val PrimaryLight = Color(0xFFBB3E1C)
private val OnPrimaryLight = Color(0xFFFFFFFF)
private val PrimaryContainerLight = Color(0xFFFFDBCF)
private val OnPrimaryContainerLight = Color(0xFF3A0B00)
private val SecondaryLight = Color(0xFF77574E)
private val OnSecondaryLight = Color(0xFFFFFFFF)
private val SecondaryContainerLight = Color(0xFFFFDBCF)
private val OnSecondaryContainerLight = Color(0xFF2C150D)
private val TertiaryLight = Color(0xFF6C5D2F)
private val OnTertiaryLight = Color(0xFFFFFFFF)
private val TertiaryContainerLight = Color(0xFFF6E1A6)
private val OnTertiaryContainerLight = Color(0xFF221B00)
private val ErrorLight = Color(0xFFBA1A1A)
private val OnErrorLight = Color(0xFFFFFFFF)
private val ErrorContainerLight = Color(0xFFFFDAD6)
private val OnErrorContainerLight = Color(0xFF410002)
private val BackgroundLight = Color(0xFFFFF8F6)
private val OnBackgroundLight = Color(0xFF231915)
private val SurfaceLight = Color(0xFFFFF8F6)
private val OnSurfaceLight = Color(0xFF231915)
private val SurfaceVariantLight = Color(0xFFF5DED6)
private val OnSurfaceVariantLight = Color(0xFF53433E)
private val OutlineLight = Color(0xFF85736C)
private val OutlineVariantLight = Color(0xFFD8C2BA)
private val InverseSurfaceLight = Color(0xFF3A2F2B)
private val InverseOnSurfaceLight = Color(0xFFFFEDE7)
private val InversePrimaryLight = Color(0xFFFFB59E)
private val ScrimLight = Color(0xFF000000)
private val SurfaceDimLight = Color(0xFFE7D7CF)
private val SurfaceBrightLight = Color(0xFFFFF8F6)
private val SurfaceContainerLowestLight = Color(0xFFFFFFFF)
private val SurfaceContainerLowLight = Color(0xFFFFF1EC)
private val SurfaceContainerLight = Color(0xFFFBEBE4)
private val SurfaceContainerHighLight = Color(0xFFF6E5DD)
private val SurfaceContainerHighestLight = Color(0xFFF0DFD8)

private val PrimaryDark = Color(0xFFFFB59E)
private val OnPrimaryDark = Color(0xFF5C1900)
private val PrimaryContainerDark = Color(0xFF7B2D10)
private val OnPrimaryContainerDark = Color(0xFFFFDBCF)
private val SecondaryDark = Color(0xFFE7BDB0)
private val OnSecondaryDark = Color(0xFF442A20)
private val SecondaryContainerDark = Color(0xFF5D4035)
private val OnSecondaryContainerDark = Color(0xFFFFDBCF)
private val TertiaryDark = Color(0xFFD9C58D)
private val OnTertiaryDark = Color(0xFF3A2F05)
private val TertiaryContainerDark = Color(0xFF524619)
private val OnTertiaryContainerDark = Color(0xFFF6E1A6)
private val ErrorDark = Color(0xFFFFB4AB)
private val OnErrorDark = Color(0xFF690005)
private val ErrorContainerDark = Color(0xFF93000A)
private val OnErrorContainerDark = Color(0xFFFFDAD6)
private val BackgroundDark = Color(0xFF1A110D)
private val OnBackgroundDark = Color(0xFFF1DFD8)
private val SurfaceDark = Color(0xFF1A110D)
private val OnSurfaceDark = Color(0xFFF1DFD8)
private val SurfaceVariantDark = Color(0xFF53433E)
private val OnSurfaceVariantDark = Color(0xFFD8C2BA)
private val OutlineDark = Color(0xFFA08D85)
private val OutlineVariantDark = Color(0xFF53433E)
private val InverseSurfaceDark = Color(0xFFF1DFD8)
private val InverseOnSurfaceDark = Color(0xFF3A2F2B)
private val InversePrimaryDark = Color(0xFFBB3E1C)
private val ScrimDark = Color(0xFF000000)
private val SurfaceDimDark = Color(0xFF1A110D)
private val SurfaceBrightDark = Color(0xFF423630)
private val SurfaceContainerLowestDark = Color(0xFF140C09)
private val SurfaceContainerLowDark = Color(0xFF231915)
private val SurfaceContainerDark = Color(0xFF271C18)
private val SurfaceContainerHighDark = Color(0xFF322722)
private val SurfaceContainerHighestDark = Color(0xFF3D312C)

val EmberLightColorScheme: ColorScheme =
    lightColorScheme(
        primary = PrimaryLight,
        onPrimary = OnPrimaryLight,
        primaryContainer = PrimaryContainerLight,
        onPrimaryContainer = OnPrimaryContainerLight,
        inversePrimary = InversePrimaryLight,
        secondary = SecondaryLight,
        onSecondary = OnSecondaryLight,
        secondaryContainer = SecondaryContainerLight,
        onSecondaryContainer = OnSecondaryContainerLight,
        tertiary = TertiaryLight,
        onTertiary = OnTertiaryLight,
        tertiaryContainer = TertiaryContainerLight,
        onTertiaryContainer = OnTertiaryContainerLight,
        error = ErrorLight,
        onError = OnErrorLight,
        errorContainer = ErrorContainerLight,
        onErrorContainer = OnErrorContainerLight,
        background = BackgroundLight,
        onBackground = OnBackgroundLight,
        surface = SurfaceLight,
        onSurface = OnSurfaceLight,
        surfaceVariant = SurfaceVariantLight,
        onSurfaceVariant = OnSurfaceVariantLight,
        outline = OutlineLight,
        outlineVariant = OutlineVariantLight,
        inverseSurface = InverseSurfaceLight,
        inverseOnSurface = InverseOnSurfaceLight,
        scrim = ScrimLight,
        surfaceTint = PrimaryLight,
        surfaceBright = SurfaceBrightLight,
        surfaceDim = SurfaceDimLight,
        surfaceContainerLowest = SurfaceContainerLowestLight,
        surfaceContainerLow = SurfaceContainerLowLight,
        surfaceContainer = SurfaceContainerLight,
        surfaceContainerHigh = SurfaceContainerHighLight,
        surfaceContainerHighest = SurfaceContainerHighestLight,
    )

val EmberDarkColorScheme: ColorScheme =
    darkColorScheme(
        primary = PrimaryDark,
        onPrimary = OnPrimaryDark,
        primaryContainer = PrimaryContainerDark,
        onPrimaryContainer = OnPrimaryContainerDark,
        inversePrimary = InversePrimaryDark,
        secondary = SecondaryDark,
        onSecondary = OnSecondaryDark,
        secondaryContainer = SecondaryContainerDark,
        onSecondaryContainer = OnSecondaryContainerDark,
        tertiary = TertiaryDark,
        onTertiary = OnTertiaryDark,
        tertiaryContainer = TertiaryContainerDark,
        onTertiaryContainer = OnTertiaryContainerDark,
        error = ErrorDark,
        onError = OnErrorDark,
        errorContainer = ErrorContainerDark,
        onErrorContainer = OnErrorContainerDark,
        background = BackgroundDark,
        onBackground = OnBackgroundDark,
        surface = SurfaceDark,
        onSurface = OnSurfaceDark,
        surfaceVariant = SurfaceVariantDark,
        onSurfaceVariant = OnSurfaceVariantDark,
        outline = OutlineDark,
        outlineVariant = OutlineVariantDark,
        inverseSurface = InverseSurfaceDark,
        inverseOnSurface = InverseOnSurfaceDark,
        scrim = ScrimDark,
        surfaceTint = PrimaryDark,
        surfaceBright = SurfaceBrightDark,
        surfaceDim = SurfaceDimDark,
        surfaceContainerLowest = SurfaceContainerLowestDark,
        surfaceContainerLow = SurfaceContainerLowDark,
        surfaceContainer = SurfaceContainerDark,
        surfaceContainerHigh = SurfaceContainerHighDark,
        surfaceContainerHighest = SurfaceContainerHighestDark,
    )

data class EmberExtendedColors(
    val success: Color,
    val onSuccess: Color,
    val successContainer: Color,
    val onSuccessContainer: Color,
    val warning: Color,
    val onWarning: Color,
    val warningContainer: Color,
    val onWarningContainer: Color,
    val info: Color,
    val onInfo: Color,
    val infoContainer: Color,
    val onInfoContainer: Color,
    val avatarFallback: Color,
    val onAvatarFallback: Color,
)

val EmberLightExtendedColors =
    EmberExtendedColors(
        success = Color(0xFF386A20),
        onSuccess = Color(0xFFFFFFFF),
        successContainer = Color(0xFFB8F397),
        onSuccessContainer = Color(0xFF042100),
        warning = Color(0xFF7A5800),
        onWarning = Color(0xFFFFFFFF),
        warningContainer = Color(0xFFFFDEA3),
        onWarningContainer = Color(0xFF261900),
        info = Color(0xFF006A6A),
        onInfo = Color(0xFFFFFFFF),
        infoContainer = Color(0xFF6FF7F6),
        onInfoContainer = Color(0xFF002020),
        avatarFallback = Color(0xFFFFDBCF),
        onAvatarFallback = Color(0xFF3A0B00),
    )

val EmberDarkExtendedColors =
    EmberExtendedColors(
        success = Color(0xFF9DD67D),
        onSuccess = Color(0xFF0B3900),
        successContainer = Color(0xFF205107),
        onSuccessContainer = Color(0xFFB8F397),
        warning = Color(0xFFF7BE49),
        onWarning = Color(0xFF402D00),
        warningContainer = Color(0xFF5D4100),
        onWarningContainer = Color(0xFFFFDEA3),
        info = Color(0xFF4CDADA),
        onInfo = Color(0xFF003737),
        infoContainer = Color(0xFF004F4F),
        onInfoContainer = Color(0xFF6FF7F6),
        avatarFallback = Color(0xFF7B2D10),
        onAvatarFallback = Color(0xFFFFDBCF),
    )

@SuppressLint("ComposeCompositionLocalUsage")
val LocalEmberExtendedColors = staticCompositionLocalOf { EmberLightExtendedColors }
