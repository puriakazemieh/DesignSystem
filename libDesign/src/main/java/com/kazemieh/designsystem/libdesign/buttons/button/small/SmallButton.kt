package com.kazemieh.designsystem.libdesign.buttons.button.small

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.RippleDrawable
import android.graphics.drawable.StateListDrawable
import android.os.Build
import android.util.AttributeSet
import android.view.View
import android.view.ViewOutlineProvider
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.core.view.marginEnd
import androidx.core.view.marginStart
import com.kazemieh.designsystem.libdesign.R
import com.kazemieh.designsystem.libdesign.buttons.button.Button
import com.kazemieh.designsystem.libdesign.buttons.button.ColorsPalletButtonImp
import com.kazemieh.designsystem.libdesign.util.dpToPx
import com.kazemieh.designsystem.libdesign.util.dpToPxInt
import com.kazemieh.designsystem.libdesign.util.marginLayoutParams
import com.kazemieh.designsystem.libdesign.util.setMargin
import com.kazemieh.designsystem.libdesign.util.updateLayoutParam
import com.kazemieh.designsystem.libdesign.util.setPaddingRelative
import com.github.ybq.android.spinkit.sprite.Sprite
import com.github.ybq.android.spinkit.style.ThreeBounce


open class SmallButton @JvmOverloads constructor(
    context: Context, attrs: AttributeSet?, defStyleAttr: Int = 0,
) : Button<StateSmallButton, StyleSmallButton, ConfigurationSmallButton>(
    context, attrs, defStyleAttr
) {


    private val threeBounce: Sprite? = ThreeBounce()
    private val progressBar: ProgressBar? = ProgressBar(context).apply {
        isVisible = state == StateSmallButton.LOADING
        binding.text.isVisible = state != StateSmallButton.LOADING
        binding.trailingIcon.isVisible = state != StateSmallButton.LOADING
        binding.leadingIcon.isVisible = state != StateSmallButton.LOADING
        indeterminateDrawable = threeBounce
        threeBounce?.color = tintEnableColor
        binding.mainLayout.addView(this)
    }

    @SuppressLint("CustomViewStyleable")
    override var buttonTypeArray =
        context.obtainStyledAttributes(attrs, R.styleable.MyButton)

    override var state: StateSmallButton = StateSmallButton.ENABLE
        set(value) {
            field = value
            state()
        }

    override var stateId = StateSmallButton.ENABLE.stateId
        set(value) {
            field = value
            state = when (value) {
                StateSmallButton.ENABLE.stateId -> {
                    StateSmallButton.ENABLE
                }

                StateSmallButton.DISABLE.stateId -> {
                    StateSmallButton.DISABLE
                }

                StateSmallButton.LOADING.stateId -> {
                    StateSmallButton.LOADING
                }

                else -> {
                    StateSmallButton.ENABLE
                }
            }
        }

    override var style: StyleSmallButton = StyleSmallButton.FIELD
        set(value) {
            field = value
            setStyleColor()
        }

    override var styleId = StyleSmallButton.FIELD.styleId
        set(value) {
            field = value
            style = when (value) {
                StyleSmallButton.FIELD.styleId -> {
                    StyleSmallButton.FIELD
                }

                StyleSmallButton.OUTLINE.styleId -> {
                    StyleSmallButton.OUTLINE
                }

                StyleSmallButton.STANDARD.styleId -> {
                    StyleSmallButton.STANDARD
                }

                StyleSmallButton.ELEVATED.styleId -> {
                    StyleSmallButton.ELEVATED
                }

                StyleSmallButton.TONAL.styleId -> {
                    StyleSmallButton.TONAL
                }


                else -> {
                    StyleSmallButton.FIELD
                }
            }
        }

    override var configuration: ConfigurationSmallButton = ConfigurationSmallButton.PRIMARY
        set(value) {
            field = value
            setStyleColor()
        }

    override var configurationId = ConfigurationSmallButton.PRIMARY.configurationId
        set(value) {
            field = value
            configuration = if (value == ConfigurationSmallButton.PRIMARY.configurationId) {
                ConfigurationSmallButton.PRIMARY
            } else {
                ConfigurationSmallButton.ERROR
            }
        }

    override var myColors: ColorsPalletButtonImp =
        ColorsPalletSmallButtonImp(context, configuration, style, state)
        set(value) {
            field = value
            normalColor = myColors.normal
            disableColor = myColors.disable
            pressedColor = myColors.pressed
            pressedMaskColor = myColors.pressedMask
            tintEnableColor = myColors.tintEnable
            tintDisableColor = myColors.tintDisable
            borderColor = myColors.border
            invalidateLayout()
        }

    override var disableColor = myColors.disable
    override var pressedColor = myColors.pressed
    override var pressedMaskColor = myColors.pressedMask
    override var tintDisableColor = myColors.tintDisable
    override var borderColor = myColors.border
    override var tintEnableColor = myColors.tintEnable
    override var normalColor = myColors.normal

    init {

        binding.text.setPaddingRelative(end = dpToPxInt(4), start = dpToPxInt(4))

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            binding.text.setTextAppearance(R.style.FontLabelMedium)
        } else {
            binding.text.setTextAppearance(context, R.style.FontLabelMedium)
        }


        binding.mainLayout.setPaddingRelative(
            end = dpToPxInt(16),
            start = dpToPxInt(16),
            top = dpToPxInt(10),
            bottom = dpToPxInt(10)
        )


        setState()
        setStyle()
        setConfiguration()

        buttonTypeArray.recycle()

    }

    override fun setImageDrawableLeadingIcon(value: Drawable?) {
        super.setImageDrawableLeadingIcon(value)
        if (value != null) {
            binding.leadingIcon.marginLayoutParams {
                width = dpToPxInt(20)
                height = dpToPxInt(20)
                marginEnd = dpToPxInt(2)
            }
        } else {
            binding.leadingIcon.marginLayoutParams {
                width = dpToPxInt(0)
                height = dpToPxInt(0)
                marginStart = dpToPxInt(0)
            }
        }
    }

    override fun setImageDrawableTrailingIcon(value: Drawable?) {
        super.setImageDrawableTrailingIcon(value)
        if (value != null) {
            binding.trailingIcon.marginLayoutParams {
                width = dpToPxInt(20)
                height = dpToPxInt(20)
                marginStart = dpToPxInt(2)
            }
        } else {
            binding.trailingIcon.marginLayoutParams {
                width = dpToPxInt(0)
                height = dpToPxInt(0)
                marginStart = dpToPxInt(0)
            }
        }

    }

    override fun invalidateLayout() {

        if (style != null)
            when (style) {
                StyleSmallButton.TONAL -> {
                    shape.setStroke(dpToPxInt(1), borderColor)
                }

                StyleSmallButton.OUTLINE -> {
                    if (state == StateSmallButton.DISABLE) {
                        shape.setStroke(dpToPxInt(0), borderColor)
                    } else {
                        shape.setStroke(dpToPxInt(1), borderColor)
                    }
                }

                else -> {
                    shape.setStroke(0, borderColor)
                }
            }

        if (style == StyleSmallButton.ELEVATED) {

            if (state == StateSmallButton.DISABLE) {
                binding.mainLayout.elevation = dpToPx(0)
                binding.mainLayout.setMargin(0)

                binding.mainLayout.updateLayoutParam {
                    height = dpToPxInt(40)
                }
                binding.cl.marginLayoutParams {
                    height = dpToPxInt(40)
                }
            } else {
                binding.cl.marginLayoutParams {
                    height = dpToPxInt(36)
                }
                binding.mainLayout.elevation = dpToPx(2)
                binding.mainLayout.outlineProvider = ViewOutlineProvider.BACKGROUND
                binding.mainLayout.setMargin(2)

                binding.mainLayout.updateLayoutParam {
                    height = dpToPxInt(36)
                }
                binding.cl.marginLayoutParams {
                    height = dpToPxInt(40)
                }

            }


        } else {
            binding.mainLayout.elevation = dpToPx(0)
            binding.mainLayout.setMargin(0)

            binding.mainLayout.updateLayoutParam {
                height = dpToPxInt(40)
            }
            binding.cl.marginLayoutParams {
                height = dpToPxInt(40)
            }
        }

        if (state == StateSmallButton.LOADING) {
            binding.mainLayout.setPaddingRelative(
                /* start =*/  dpToPxInt(0),
                /* top =*/ dpToPxInt(0),
                /* end =*/ dpToPxInt(0),
                /* bottom =*/ dpToPxInt(0)
            )
        }

        threeBounce?.color = tintEnableColor
        binding.text.setTextColor(getStateColorTint())
        binding.trailingIcon.imageTintList = getStateColorTint()
        binding.leadingIcon.imageTintList = getStateColorTint()
        binding.mainLayout.background = stateListDrawable()

        requestLayout()
    }

    override fun stateListDrawable(): StateListDrawable {

        shape.setColor(normalColor)

        val maskDrawable = getRippleColor(pressedMaskColor)

        val rippleDrawable =
            RippleDrawable(
                getPressedColorSelector(),
                shape,
                maskDrawable
            )

        val disableDrawable =
            (shape.constantState?.newDrawable()?.mutate() as GradientDrawable).apply {
                disableColor?.let { setColor(it) }
            }

        val normalDrawable =
            (shape.constantState?.newDrawable()?.mutate() as GradientDrawable).apply {
                setColor(normalColor)
            }

        val stateListDrawable = StateListDrawable()

        if (state == StateSmallButton.ENABLE)
            stateListDrawable.addState(
                intArrayOf(android.R.attr.state_pressed),
                rippleDrawable
            )

        stateListDrawable.addState(
            intArrayOf(-android.R.attr.state_enabled),
            disableDrawable
        )

        stateListDrawable.addState(
            intArrayOf(),
            normalDrawable
        )
        return stateListDrawable
    }

    override fun state() {
        setStyleColor()
        progressBar?.isVisible = state == StateSmallButton.LOADING
        when (state) {
            StateSmallButton.ENABLE -> {

                binding.text.isVisible = true
                binding.trailingIcon.isVisible = true
                binding.leadingIcon.isVisible = true

                binding.text.isEnabled = true
                binding.mainLayout.isEnabled = true
                binding.trailingIcon.isEnabled = true
                binding.leadingIcon.isEnabled = true
                binding.cl.isEnabled = true

            }

            StateSmallButton.DISABLE -> {

                binding.text.isVisible = true
                binding.trailingIcon.isVisible = true
                binding.leadingIcon.isVisible = true

                binding.text.isEnabled = false
                binding.mainLayout.isEnabled = false
                binding.trailingIcon.isEnabled = false
                binding.leadingIcon.isEnabled = false
                binding.cl.isEnabled = false
            }

            StateSmallButton.LOADING -> {

                binding.text.isEnabled = true
                binding.mainLayout.isEnabled = true
                binding.trailingIcon.isEnabled = true
                binding.leadingIcon.isEnabled = true
                binding.cl.isEnabled = true

                binding.text.isVisible = false
                binding.trailingIcon.isVisible = false
                binding.leadingIcon.isVisible = false

                binding.cl.isClickable = false
                binding.leadingIcon.isClickable = false
                binding.trailingIcon.isClickable = false
                binding.mainLayout.isClickable = false
                binding.leadingIcon.isClickable = false

                binding.cl.isFocusable = false
                binding.leadingIcon.isFocusable = false
                binding.trailingIcon.isFocusable = false
                binding.mainLayout.isFocusable = false
                binding.leadingIcon.isFocusable = false
            }

        }
    }

    override fun setStyleColor() {
        myColors = ColorsPalletSmallButtonImp(context, configuration, style, state)
    }

    private fun setState() {
        stateId = buttonTypeArray.getInt(
            R.styleable.smallButton_stateSmallButton,
            StateSmallButton.ENABLE.stateId
        )
    }

    private fun setStyle() {
        styleId = buttonTypeArray.getInt(
            R.styleable.smallButton_styleSmallButton,
            StyleSmallButton.FIELD.styleId
        )

    }

    private fun setConfiguration() {
        configurationId = buttonTypeArray.getInt(
            R.styleable.smallButton_configurationSmallButton,
            ConfigurationSmallButton.PRIMARY.configurationId
        )
    }


    override fun onClick(v: View?) {
        super.onClick(v)
    }
}