package com.kazemieh.designsystem.libdesign

import android.annotation.SuppressLint
import android.content.Context
import androidx.core.content.ContextCompat.getColor

@SuppressLint("ResourceType")
abstract class ColorsPalletImp(context: Context) : ColorsPallet {

    val alpha38 = (38 * 2.55).toInt()
    val alpha12 = (12 * 2.55).toInt()

    override val primary: Int = getColor(context, ColorsInt.Primary)
    override val onPrimary: Int = getColor(context, ColorsInt.onPrimary)
    override val primaryContainer: Int = getColor(context, ColorsInt.primaryContainer)
    override val onPrimaryContainer: Int = getColor(context, ColorsInt.onPrimaryContainer)
    override val secondary: Int = getColor(context, ColorsInt.secondary)
    override val onSecondary: Int = getColor(context, ColorsInt.onSecondary)
    override val secondaryContainer: Int = getColor(context, ColorsInt.secondaryContainer)
    override val onSecondaryContainer: Int = getColor(context, ColorsInt.onSecondaryContainer)
    override val tertiary: Int = getColor(context, ColorsInt.tertiary)
    override val onTertiary: Int = getColor(context, ColorsInt.onTertiary)
    override val tertiaryContainer: Int = getColor(context, ColorsInt.tertiaryContainer)
    override val onTertiaryContainer: Int = getColor(context, ColorsInt.onTertiaryContainer)
    override val error: Int = getColor(context, ColorsInt.error)
    override val errorContainer: Int = getColor(context, ColorsInt.errorContainer)
    override val onError: Int = getColor(context, ColorsInt.onError)
    override val onErrorContainer: Int = getColor(context, ColorsInt.onErrorContainer)
    override val background: Int = getColor(context, ColorsInt.background)
    override val onBackground: Int = getColor(context, ColorsInt.onBackground)
    override val surface: Int = getColor(context, ColorsInt.surface)
    override val onSurface: Int = getColor(context, ColorsInt.onSurface)
    override val surfaceVariant: Int = getColor(context, ColorsInt.surfaceVariant)
    override val onSurfaceVariant: Int = getColor(context, ColorsInt.onSurfaceVariant)
    override val outline: Int = getColor(context, ColorsInt.outline)
    override val inverseOnSurface: Int = getColor(context, ColorsInt.inverseOnSurface)
    override val inverseSurface: Int = getColor(context, ColorsInt.inverseSurface)
    override val inversePrimary: Int = getColor(context, ColorsInt.inversePrimary)
    override val shadow: Int = getColor(context, ColorsInt.shadow)
    override val surfaceTint: Int = getColor(context, ColorsInt.surfaceTint)
    override val outlineVariant: Int = getColor(context, ColorsInt.outlineVariant)
    override val scrim: Int = getColor(context, ColorsInt.scrim)


}