/*
 * Copyright (C) 2025 The Neuboard Contributors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.neuboard.lib.color.schemes

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

private val primaryLight = Color(0xFF3B6939)
private val onPrimaryLight = Color(0xFFFFFFFF)
private val primaryContainerLight = Color(0xFFBCF0B4)
private val onPrimaryContainerLight = Color(0xFF235024)
private val secondaryLight = Color(0xFF52634F)
private val onSecondaryLight = Color(0xFFFFFFFF)
private val secondaryContainerLight = Color(0xFFD5E8CF)
private val onSecondaryContainerLight = Color(0xFF3B4B38)
private val tertiaryLight = Color(0xFF38656A)
private val onTertiaryLight = Color(0xFFFFFFFF)
private val tertiaryContainerLight = Color(0xFFBCEBF0)
private val onTertiaryContainerLight = Color(0xFF1F4D52)
private val errorLight = Color(0xFFBA1A1A)
private val onErrorLight = Color(0xFFFFFFFF)
private val errorContainerLight = Color(0xFFFFDAD6)
private val onErrorContainerLight = Color(0xFF93000A)
private val backgroundLight = Color(0xFFF7FBF1)
private val onBackgroundLight = Color(0xFF191D17)
private val surfaceLight = Color(0xFFF7FBF1)
private val onSurfaceLight = Color(0xFF191D17)
private val surfaceVariantLight = Color(0xFFDEE5D8)
private val onSurfaceVariantLight = Color(0xFF424940)
private val outlineLight = Color(0xFF72796F)
private val outlineVariantLight = Color(0xFFC2C9BD)
private val scrimLight = Color(0xFF000000)
private val inverseSurfaceLight = Color(0xFF2D322C)
private val inverseOnSurfaceLight = Color(0xFFEFF2E9)
private val inversePrimaryLight = Color(0xFFA1D39A)
private val surfaceDimLight = Color(0xFFD8DBD2)
private val surfaceBrightLight = Color(0xFFF7FBF1)
private val surfaceContainerLowestLight = Color(0xFFFFFFFF)
private val surfaceContainerLowLight = Color(0xFFF1F5EC)
private val surfaceContainerLight = Color(0xFFECEFE6)
private val surfaceContainerHighLight = Color(0xFFE6E9E0)
private val surfaceContainerHighestLight = Color(0xFFE0E4DB)
private val primaryDark = Color(0xFFA1D39A)
private val onPrimaryDark = Color(0xFF0A390F)
private val primaryContainerDark = Color(0xFF235024)
private val onPrimaryContainerDark = Color(0xFFBCF0B4)
private val secondaryDark = Color(0xFFBACCB3)
private val onSecondaryDark = Color(0xFF253423)
private val secondaryContainerDark = Color(0xFF3B4B38)
private val onSecondaryContainerDark = Color(0xFFD5E8CF)
private val tertiaryDark = Color(0xFFA0CFD4)
private val onTertiaryDark = Color(0xFF00363B)
private val tertiaryContainerDark = Color(0xFF1F4D52)
private val onTertiaryContainerDark = Color(0xFFBCEBF0)
private val errorDark = Color(0xFFFFB4AB)
private val onErrorDark = Color(0xFF690005)
private val errorContainerDark = Color(0xFF93000A)
private val onErrorContainerDark = Color(0xFFFFDAD6)
private val backgroundDark = Color(0xFF10140F)
private val onBackgroundDark = Color(0xFFE0E4DB)
private val surfaceDark = Color(0xFF10140F)
private val onSurfaceDark = Color(0xFFE0E4DB)
private val surfaceVariantDark = Color(0xFF424940)
private val onSurfaceVariantDark = Color(0xFFC2C9BD)
private val outlineDark = Color(0xFF8C9388)
private val outlineVariantDark = Color(0xFF424940)
private val scrimDark = Color(0xFF000000)
private val inverseSurfaceDark = Color(0xFFE0E4DB)
private val inverseOnSurfaceDark = Color(0xFF2D322C)
private val inversePrimaryDark = Color(0xFF3B6939)
private val surfaceDimDark = Color(0xFF10140F)
private val surfaceBrightDark = Color(0xFF363A34)
private val surfaceContainerLowestDark = Color(0xFF0B0F0A)
private val surfaceContainerLowDark = Color(0xFF191D17)
private val surfaceContainerDark = Color(0xFF1D211B)
private val surfaceContainerHighDark = Color(0xFF272B25)
private val surfaceContainerHighestDark = Color(0xFF323630)


val neuboardDefaultKeyboardLightScheme = lightColorScheme(
    primary = primaryLight,
    onPrimary = onPrimaryLight,
    primaryContainer = primaryContainerLight,
    onPrimaryContainer = onPrimaryContainerLight,
    secondary = secondaryLight,
    onSecondary = onSecondaryLight,
    secondaryContainer = secondaryContainerLight,
    onSecondaryContainer = onSecondaryContainerLight,
    tertiary = tertiaryLight,
    onTertiary = onTertiaryLight,
    tertiaryContainer = tertiaryContainerLight,
    onTertiaryContainer = onTertiaryContainerLight,
    error = errorLight,
    onError = onErrorLight,
    errorContainer = errorContainerLight,
    onErrorContainer = onErrorContainerLight,
    background = backgroundLight,
    onBackground = onBackgroundLight,
    surface = surfaceLight,
    onSurface = onSurfaceLight,
    surfaceVariant = surfaceVariantLight,
    onSurfaceVariant = onSurfaceVariantLight,
    outline = outlineLight,
    outlineVariant = outlineVariantLight,
    scrim = scrimLight,
    inverseSurface = inverseSurfaceLight,
    inverseOnSurface = inverseOnSurfaceLight,
    inversePrimary = inversePrimaryLight,
    surfaceDim = surfaceDimLight,
    surfaceBright = surfaceBrightLight,
    surfaceContainerLowest = surfaceContainerLowestLight,
    surfaceContainerLow = surfaceContainerLowLight,
    surfaceContainer = surfaceContainerLight,
    surfaceContainerHigh = surfaceContainerHighLight,
    surfaceContainerHighest = surfaceContainerHighestLight,
)

val neuboardDefaultKeyboardDarkScheme = darkColorScheme(
    primary = primaryDark,
    onPrimary = onPrimaryDark,
    primaryContainer = primaryContainerDark,
    onPrimaryContainer = onPrimaryContainerDark,
    secondary = secondaryDark,
    onSecondary = onSecondaryDark,
    secondaryContainer = secondaryContainerDark,
    onSecondaryContainer = onSecondaryContainerDark,
    tertiary = tertiaryDark,
    onTertiary = onTertiaryDark,
    tertiaryContainer = tertiaryContainerDark,
    onTertiaryContainer = onTertiaryContainerDark,
    error = errorDark,
    onError = onErrorDark,
    errorContainer = errorContainerDark,
    onErrorContainer = onErrorContainerDark,
    background = backgroundDark,
    onBackground = onBackgroundDark,
    surface = surfaceDark,
    onSurface = onSurfaceDark,
    surfaceVariant = surfaceVariantDark,
    onSurfaceVariant = onSurfaceVariantDark,
    outline = outlineDark,
    outlineVariant = outlineVariantDark,
    scrim = scrimDark,
    inverseSurface = inverseSurfaceDark,
    inverseOnSurface = inverseOnSurfaceDark,
    inversePrimary = inversePrimaryDark,
    surfaceDim = surfaceDimDark,
    surfaceBright = surfaceBrightDark,
    surfaceContainerLowest = surfaceContainerLowestDark,
    surfaceContainerLow = surfaceContainerLowDark,
    surfaceContainer = surfaceContainerDark,
    surfaceContainerHigh = surfaceContainerHighDark,
    surfaceContainerHighest = surfaceContainerHighestDark,
)
