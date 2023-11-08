package com.kazemieh.designsystem.libdesign.buttons.button

import android.content.Context
import com.kazemieh.designsystem.libdesign.buttons.base.ColorsPalletBaseButtonImp

abstract class ColorsPalletButtonImp(context: Context) : ColorsPalletBaseButtonImp(context) {

    abstract val disable: Int?
    abstract val tintEnable: Int
    abstract val tintDisable: Int
    abstract val border: Int

}