package com.kazemieh.designsystem.libdesign.buttons.base

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.content.res.TypedArray
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.RippleDrawable
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.StateListDrawable
import android.graphics.drawable.shapes.RoundRectShape
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.View.OnClickListener
import android.view.View.OnLongClickListener
import android.view.View.OnTouchListener
import android.view.ViewOutlineProvider
import android.widget.FrameLayout
import android.widget.ProgressBar
import androidx.core.view.isVisible
import com.github.ybq.android.spinkit.sprite.Sprite
import com.github.ybq.android.spinkit.style.ThreeBounce
import com.kazemieh.designsystem.libdesign.R
import com.kazemieh.designsystem.libdesign.databinding.ButtonBinding
import com.kazemieh.designsystem.libdesign.util.dpToPx
import com.kazemieh.designsystem.libdesign.util.dpToPxInt
import com.kazemieh.designsystem.libdesign.util.marginLayoutParams
import com.kazemieh.designsystem.libdesign.util.setMargin
import com.kazemieh.designsystem.libdesign.util.setPaddingRelative
import com.kazemieh.designsystem.libdesign.util.updateLayoutParam
import java.util.Arrays


@SuppressLint("ClickableViewAccessibility", "CustomViewStyleable")
open class BaseButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet?,
    defStyleAttr: Int = 0
) : FrameLayout(
    context,
    attrs,
    defStyleAttr
), OnClickListener, OnLongClickListener, OnTouchListener {

    companion object {
        val TAG = "BaseButton"
    }

    protected val binding: ButtonBinding
    protected val shape: GradientDrawable
    private val typeArray: TypedArray

    private lateinit var onClickListener: () -> Unit
    private lateinit var onLongClickListener: () -> Unit
    private lateinit var onTouchListener: (motionEvent: MotionEvent?) -> Boolean


//    @SuppressLint("CustomViewStyleable")
//     var buttonTypeArray =
//        context.obtainStyledAttributes(attrs, R.styleable.smallButton)

     var state: StateButton = StateButton.ENABLE
        set(value) {
            field = value
            state()
        }

     var stateId = StateButton.ENABLE.stateId
        set(value) {
            field = value
            state = when (value) {
                StateButton.ENABLE.stateId -> {
                    StateButton.ENABLE
                }

                StateButton.DISABLE.stateId -> {
                    StateButton.DISABLE
                }

                StateButton.LOADING.stateId -> {
                    StateButton.LOADING
                }

                else -> {
                    StateButton.ENABLE
                }
            }
        }

     var style: StyleButton = StyleButton.FIELD
        set(value) {
            field = value
            setStyleColor()
        }

     var styleId = StyleButton.FIELD.styleId
        set(value) {
            field = value
            style = when (value) {
                StyleButton.FIELD.styleId -> {
                    StyleButton.FIELD
                }

                StyleButton.OUTLINE.styleId -> {
                    StyleButton.OUTLINE
                }

                StyleButton.STANDARD.styleId -> {
                    StyleButton.STANDARD
                }

                StyleButton.ELEVATED.styleId -> {
                    StyleButton.ELEVATED
                }

                StyleButton.TONAL.styleId -> {
                    StyleButton.TONAL
                }


                else -> {
                    StyleButton.FIELD
                }
            }
        }

     var configuration: ConfigurationButton = ConfigurationButton.PRIMARY
        set(value) {
            field = value
            setStyleColor()
        }

     var configurationId = ConfigurationButton.PRIMARY.configurationId
        set(value) {
            field = value
            configuration = if (value == ConfigurationButton.PRIMARY.configurationId) {
                ConfigurationButton.PRIMARY
            } else {
                ConfigurationButton.ERROR
            }
        }

     var myColors: ColorsPalletButtonImp =
        ColorsPalletButtonImp(context, configuration, style, state)
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

    var disableColor = myColors.disable
    var pressedColor = myColors.pressed
    var pressedMaskColor = myColors.pressedMask
    var tintDisableColor = myColors.tintDisable
    var borderColor = myColors.border
    var tintEnableColor = myColors.tintEnable
    var normalColor = myColors.normal

    private var cornerRadiusId = CornerRadius.ROUND_100.cornerRadiusId
        set(value) {
            field = value
            when (value) {
                CornerRadius.ROUND_8.cornerRadiusId -> {
                    cornerRadius = CornerRadius.ROUND_8
                }

                CornerRadius.ROUND_100.cornerRadiusId -> {
                    cornerRadius = CornerRadius.ROUND_100
                }

                else -> {
                    applyCorner(value)
                }
            }
        }

    var cornerRadius: Any = CornerRadius.ROUND_100
        set(value) {
            field = value
            Log.d(TAG, "cornerRadius: $value ")
            if (value is CornerRadius)
                applyCorner(value.cornerRadiusId)
            else if (value is Int)
                applyCorner(value)
        }

    var leadingIcon: Drawable? = null
        set(value) {
            field = value
            setImageDrawableLeadingIcon(value)
        }

    var trailingIcon: Drawable? = null
        set(value) {
            field = value
            setImageDrawableTrailingIcon(value)
        }

    var text: String = resources.getString(R.string.app_name)
        set(value) {
            field = value
            binding.text.text = text
        }

    init {

        val inflater: LayoutInflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        binding = ButtonBinding.inflate(inflater, this, true)

        binding.mainLayout.layoutDirection = LAYOUT_DIRECTION_RTL

        binding.text.includeFontPadding = false
        binding.cl.setOnClickListener(this)
        binding.cl.setOnLongClickListener(this)
        binding.cl.setOnTouchListener(this)

        shape = GradientDrawable()
        shape.shape = GradientDrawable.RECTANGLE

        typeArray = context.obtainStyledAttributes(attrs, R.styleable.MyButton)

        setCornerRadius()
        setLeadingIcon()
        setTrailingIcon()
        setText()

        binding.text.setPaddingRelative(end = dpToPxInt(4), start = dpToPxInt(4))

        binding.text.setTextAppearance(R.style.FontLabelMedium)


        binding.mainLayout.setPaddingRelative(
            end = dpToPxInt(16),
            start = dpToPxInt(16),
            top = dpToPxInt(10),
            bottom = dpToPxInt(10)
        )


        setState()
        setStyle()
        setConfiguration()

        typeArray.recycle()



//        buttonTypeArray.recycle()



    }


    private val threeBounce: Sprite? = ThreeBounce()
    private val progressBar: ProgressBar? = ProgressBar(context).apply {
        isVisible = state == StateButton.LOADING
        binding.text.isVisible = state != StateButton.LOADING
        binding.trailingIcon.isVisible = state != StateButton.LOADING
        binding.leadingIcon.isVisible = state != StateButton.LOADING
        indeterminateDrawable = threeBounce
        threeBounce?.color = tintEnableColor
        binding.mainLayout.addView(this)
    }


    fun getStateColorTint(tintEnable: Int? = tintEnableColor): ColorStateList {
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

    private fun setCornerRadius() {
        cornerRadiusId = typeArray.getInt(
            R.styleable.MyButton_myCornerRadius,
            CornerRadius.ROUND_8.cornerRadiusId
        )
    }

    private fun setLeadingIcon() {
        leadingIcon = typeArray.getDrawable(R.styleable.MyButton_leadingIcon)
    }

    private fun setTrailingIcon() {
        trailingIcon = typeArray.getDrawable(R.styleable.MyButton_trailingIcon)
    }

    private fun setText() {
        text = typeArray.getString(R.styleable.MyButton_text)
            ?: resources.getString(R.string.app_name)
    }

    protected open fun applyCorner(value: Int) {
        val corner = dpToPx(value)
        shape.cornerRadii =
            floatArrayOf(corner, corner, corner, corner, corner, corner, corner, corner)
        invalidateLayout()
    }

    protected fun setOnClickListener(event: () -> Unit) {
        this.onClickListener = event
    }

    protected fun setonLongClickListener(event: () -> Unit) {
        this.onLongClickListener = event
    }

    protected fun setonTouchListener(event: (motionEvent: MotionEvent?) -> Boolean) {
        this.onTouchListener = event
    }

    protected fun getRippleColor(color: Int): Drawable {
        val outerRadii = FloatArray(8)
        Arrays.fill(outerRadii,
            ((cornerRadius as? CornerRadius)?.cornerRadiusId ?: (cornerRadius as? Int)
            ?: 0).toFloat()
        )
        val r = RoundRectShape(outerRadii, null, null)
        val shapeDrawable = ShapeDrawable(r)
        shapeDrawable.paint.color = color
        return shapeDrawable
    }

    protected fun getPressedColorSelector(): ColorStateList {
        return ColorStateList(
            arrayOf(
                intArrayOf(android.R.attr.state_pressed),
                intArrayOf(android.R.attr.state_focused),
                intArrayOf(android.R.attr.state_activated)
            ), intArrayOf(
                pressedColor,
                pressedColor,
                pressedColor
            )
        )
    }


     fun setImageDrawableLeadingIcon(value: Drawable?) {
        binding.leadingIcon.setImageDrawable(value)
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

     fun setImageDrawableTrailingIcon(value: Drawable?) {
        binding.trailingIcon.setImageDrawable(value)
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


     open fun invalidateLayout() {

        if (style != null)
            when (style) {
                StyleButton.TONAL -> {
                    shape.setStroke(dpToPxInt(1), borderColor)
                }

                StyleButton.OUTLINE -> {
                    if (state == StateButton.DISABLE) {
                        shape.setStroke(dpToPxInt(0), borderColor)
                    } else {
                        shape.setStroke(dpToPxInt(1), borderColor)
                    }
                }

                else -> {
                    shape.setStroke(0, borderColor)
                }
            }

        if (style == StyleButton.ELEVATED) {

            if (state == StateButton.DISABLE) {
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

        if (state == StateButton.LOADING) {
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


     fun stateListDrawable(): StateListDrawable {

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

        if (state == StateButton.ENABLE)
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

     fun state() {
        setStyleColor()
        progressBar?.isVisible = state == StateButton.LOADING
        when (state) {
            StateButton.ENABLE -> {

                binding.text.isVisible = true
                binding.trailingIcon.isVisible = true
                binding.leadingIcon.isVisible = true

                binding.text.isEnabled = true
                binding.mainLayout.isEnabled = true
                binding.trailingIcon.isEnabled = true
                binding.leadingIcon.isEnabled = true
                binding.cl.isEnabled = true

            }

            StateButton.DISABLE -> {

                binding.text.isVisible = true
                binding.trailingIcon.isVisible = true
                binding.leadingIcon.isVisible = true

                binding.text.isEnabled = false
                binding.mainLayout.isEnabled = false
                binding.trailingIcon.isEnabled = false
                binding.leadingIcon.isEnabled = false
                binding.cl.isEnabled = false
            }

            StateButton.LOADING -> {

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


     fun setStyleColor() {
        myColors = ColorsPalletButtonImp(context, configuration, style, state)
    }

    private fun setState() {
        stateId = typeArray.getInt(
            R.styleable.MyButton_stateButton,
            StateButton.ENABLE.stateId
        )
    }

    private fun setStyle() {
        styleId = typeArray.getInt(
            R.styleable.MyButton_styleButton,
            StyleButton.FIELD.styleId
        )

    }

    private fun setConfiguration() {
        configurationId = typeArray.getInt(
            R.styleable.MyButton_configurationButton,
            ConfigurationButton.PRIMARY.configurationId
        )
    }


    override fun onClick(v: View?) {
        if (::onClickListener.isInitialized) {
            onClickListener.invoke()
        }
    }

    override fun onLongClick(v: View?): Boolean {
        if (::onLongClickListener.isInitialized) {
            onLongClickListener.invoke()
        }
        return true
    }

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        if (::onTouchListener.isInitialized) {
            return onTouchListener.invoke(event)
        }
        return false
    }


}
