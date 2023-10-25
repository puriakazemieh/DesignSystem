package com.kazemieh.designsystem.libdesign

import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable

interface ColorsPallet {

    fun drawable(color: (ColorsPallet.() -> Int)): Drawable = ColorDrawable(color.invoke(this))


    val primary: Int
    val onPrimary: Int
    val primaryContainer: Int
    val onPrimaryContainer: Int
    val secondary: Int
    val onSecondary: Int
    val secondaryContainer: Int
    val onSecondaryContainer: Int
    val tertiary: Int
    val onTertiary: Int
    val tertiaryContainer: Int
    val onTertiaryContainer: Int
    val error: Int
    val errorContainer: Int
    val onError: Int
    val onErrorContainer: Int
    val background: Int
    val onBackground: Int
    val surface: Int
    val onSurface: Int
    val surfaceVariant: Int
    val onSurfaceVariant: Int
    val outline: Int
    val inverseOnSurface: Int
    val inverseSurface: Int
    val inversePrimary: Int
    val shadow: Int
    val surfaceTint: Int
    val outlineVariant: Int
    val scrim: Int


}