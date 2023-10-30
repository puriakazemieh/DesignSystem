package com.kazemieh.designsystem.libdesign.buttons

import android.content.Context
import com.kazemieh.designsystem.libdesign.color.ColorsPalletImp

abstract class ColorsPalletBaseButtonImp(context: Context) : ColorsPalletImp(context) {

    abstract val normal: Int
    abstract val pressed: Int
    abstract val pressedMask: Int

}