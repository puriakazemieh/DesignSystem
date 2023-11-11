package com.kazemieh.designsystem.libdesign.util

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Build
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment


fun Int.dpToPx(context: Context) = ((this * context.resources.displayMetrics.density)+ 0.5).toInt()

fun View.dpToPx(number: Int) = (number * context.resources.displayMetrics.density)

fun View.dpToPxInt(number: Int) =
    ((number * context.resources.displayMetrics.density) + 0.5).toInt()


inline fun <reified T : ViewGroup.LayoutParams> View.layoutParams(block: T.() -> Unit) {
    if (layoutParams is T) block(layoutParams as T)
}

inline fun View.layoutParam(block: ViewGroup.LayoutParams.() -> Unit) {
    block(layoutParams as ViewGroup.LayoutParams)
}

inline fun View.marginLayoutParams(block: ViewGroup.MarginLayoutParams.() -> Unit) {
    block(layoutParams as ViewGroup.MarginLayoutParams)
}

inline fun View.updateLayoutParam(block: ViewGroup.MarginLayoutParams.() -> Unit) {
    layoutParams = layoutParams.apply {
        block(layoutParams as ViewGroup.MarginLayoutParams)
    }

}



fun View.setPaddingRelative(
    top: Int? = null,
    bottom: Int? = null,
    end: Int? = null,
    start: Int? = null
) {
    setPaddingRelative(
        start ?: paddingStart,
        top ?: paddingTop,
        end ?: paddingEnd,
        bottom ?: paddingBottom
    )
}

fun View.setPaddingRelative(
    padding: Int? = null,
) {
    setPaddingRelative(
        padding ?: paddingStart,
        padding ?: paddingTop,
        padding ?: paddingEnd,
        padding ?: paddingBottom
    )
}

fun View.setMargin(
    left: Int? = null,
    top: Int? = null,
    right: Int? = null,
    bottom: Int? = null,
//    end: Int? = null,
//    start: Int? = null
) {
    marginLayoutParams {
        left?.run { leftMargin = dpToPxInt(this) }
        top?.run { topMargin = dpToPxInt(this) }
        right?.run { rightMargin = dpToPxInt(this) }
        bottom?.run { bottomMargin = dpToPxInt(this) }
//        end?.run { marginEnd = dpToPxInt(this) }
//        start?.run { marginStart = dpToPxInt(this) }
    }
}

fun View.setMargin(
    margin: Int? = null,
) {
    marginLayoutParams {
        margin?.run { leftMargin = dpToPxInt(this) }
        margin?.run { topMargin = dpToPxInt(this) }
        margin?.run { rightMargin = dpToPxInt(this) }
        margin?.run { bottomMargin = dpToPxInt(this) }
//        margin?.run { marginEnd = dpToPxInt(this) }
//        margin?.run { marginStart = dpToPxInt(this) }
    }
}

fun View.setPadding(
    left: Int? = null,
    top: Int? = null,
    right: Int? = null,
    bottom: Int? = null
) {
    setPadding(
        left ?: paddingLeft,
        top ?: paddingTop,
        right ?: paddingRight,
        bottom ?: paddingBottom
    )

}



fun Int.setAlpha(alpha: Int): Int {
    val red = Color.red(this)
    val green = Color.green(this)
    val blue = Color.blue(this)
    return Color.argb(alpha, red, green, blue)
}
