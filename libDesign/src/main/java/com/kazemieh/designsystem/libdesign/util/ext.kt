package com.kazemieh.designsystem.libdesign.util

import android.content.Context
import android.view.View


fun Int.dpToPx(context: Context) = ((this * context.resources.displayMetrics.density)+ 0.5).toInt()

fun View.dpToPx(number: Int) = (number * context.resources.displayMetrics.density)

fun View.dpToPxInt(number: Int) =
    ((number * context.resources.displayMetrics.density) + 0.5).toInt()
