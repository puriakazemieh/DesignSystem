package com.kazemieh.designsystem.libdesign.buttons.button

import android.content.Context
import android.content.res.ColorStateList
import android.content.res.TypedArray
import android.util.AttributeSet
import com.kazemieh.designsystem.libdesign.buttons.base.BaseButton

abstract class Button<State, Style, Configuration> @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet?,
    defStyleAttr: Int = 0,
) : BaseButton<ColorsPalletButtonImp>(
    context, attrs, defStyleAttr
) {

    protected abstract var stateId: Int
    protected abstract var styleId: Int
    protected abstract var configurationId: Int
    protected abstract var disableColor: Int?
    protected abstract var tintDisableColor: Int
    protected abstract var borderColor: Int

    abstract override var myColors: ColorsPalletButtonImp

    abstract var buttonTypeArray: TypedArray

    abstract var configuration: Configuration
    abstract var style: Style
    abstract var state: State

    protected abstract fun state()

    override fun applyCorner(value: Int) {
        super.applyCorner(value)
        invalidateLayout()
    }

    override fun getStateColorTint(tintEnable: Int?): ColorStateList {
        return ColorStateList(
            arrayOf(
                intArrayOf(-android.R.attr.state_enabled),
                intArrayOf()
            ), tintEnable?.let {
                intArrayOf(
                    tintDisableColor,
                    it
                )
            }
        )
    }


}