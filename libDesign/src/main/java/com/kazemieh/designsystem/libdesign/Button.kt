package com.kazemieh.designsystem.libdesign

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
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.ViewOutlineProvider
import android.widget.FrameLayout
import android.widget.ProgressBar
import androidx.core.view.isVisible
import com.github.ybq.android.spinkit.sprite.Sprite
import com.github.ybq.android.spinkit.style.ThreeBounce
import com.kazemieh.designsystem.libdesign.databinding.ButtonBinding
import com.kazemieh.designsystem.libdesign.util.dpToPx
import com.kazemieh.designsystem.libdesign.util.dpToPxInt
import com.kazemieh.designsystem.libdesign.util.setMargin
import com.kazemieh.designsystem.libdesign.util.setPaddingRelative
import java.util.Arrays
import kotlin.math.min


@SuppressLint("ClickableViewAccessibility", "CustomViewStyleable")
class Button @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet?,
    defStyleAttr: Int = 0
) : FrameLayout(
    context,
    attrs,
    defStyleAttr
) {

    private val inflater: LayoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    private var binding: ButtonBinding = ButtonBinding.inflate(inflater, this, true)

    private val shape: GradientDrawable by lazy {
        GradientDrawable().apply {
            shape = GradientDrawable.RECTANGLE
        }
    }

    private val typeArray: TypedArray = context.obtainStyledAttributes(attrs, R.styleable.MyButton)
    private val threeBounce: Sprite by lazy { ThreeBounce() }

    private lateinit var onClickListener: () -> Unit
    private lateinit var onLongClickListener: () -> Unit
    private lateinit var onTouchListener: (motionEvent: MotionEvent?) -> Boolean

    private val progressBar: ProgressBar? by lazy {
        ProgressBar(context).apply {
            isVisible = state == StateButton.LOADING
            binding.text.isVisible = state != StateButton.LOADING
            binding.trailingIcon.isVisible = state != StateButton.LOADING
            binding.leadingIcon.isVisible = state != StateButton.LOADING
            indeterminateDrawable = threeBounce
            threeBounce.color = tintEnableColor
            binding.mainLayout.addView(this)
        }
    }

    var buttonType: ButtonType = ButtonType.NORMAL
        set(value) {
            field = value
            if (value == ButtonType.NORMAL) {
                binding.trailingIcon.isVisible = true
                binding.text.isVisible = true
            } else {
                binding.trailingIcon.isVisible = false
                binding.text.isVisible = false
            }
            requestLayout()
        }

    private var buttonTypeId = ButtonType.NORMAL.typeId
        set(value) {
            field = value
            buttonType = when (value) {
                ButtonType.NORMAL.typeId -> {
                    ButtonType.NORMAL
                }

                ButtonType.ICON.typeId -> {
                    ButtonType.ICON
                }

                else -> {
                    ButtonType.NORMAL
                }
            }
        }

    var state: StateButton = StateButton.ENABLE
        set(value) {
            field = value
            state()
        }

    private var stateId = StateButton.ENABLE.stateId
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
            handleStyle()
        }

    private var styleId = StyleButton.FIELD.styleId
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

    private var configurationId = ConfigurationButton.PRIMARY.configurationId
        set(value) {
            field = value
            configuration = if (value == ConfigurationButton.PRIMARY.configurationId) {
                ConfigurationButton.PRIMARY
            } else {
                ConfigurationButton.ERROR
            }
        }

    var cornerRadius: Any = CornerRadius.ROUND_100
        set(value) {
            field = value
            if (value is CornerRadius)
                applyCorner(value.cornerRadiusId)
            else if (value is Int)
                applyCorner(value)
        }

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

    var text: String? = null
        set(value) {
            field = value
            handleText()
        }

    private var myColors: ColorsPalletButtonImp =
        ColorsPalletButtonImp(context, configuration, style, state)
        set(value) {
            field = value
            setColor()
            setStroke()
            threeBounce.color = tintEnableColor
            binding.mainLayout.background = stateListDrawable()
            binding.text.setTextColor(getStateColorTint())
            binding.trailingIcon.imageTintList = getStateColorTint()
            binding.leadingIcon.imageTintList = getStateColorTint()
        }

    private var disableColor = myColors.disable
    private var pressedColor = myColors.pressed
    private var pressedMaskColor = myColors.pressedMask
    private var tintDisableColor = myColors.tintDisable
    private var borderColor = myColors.border
    private var tintEnableColor = myColors.tintEnable
    private var normalColor = myColors.normal

    init {
        clickListener()

        binding.mainLayout.layoutDirection = LAYOUT_DIRECTION_RTL
        binding.text.includeFontPadding = false
        binding.text.setTextAppearance(R.style.FontLabelMedium)


        setText()
        setLeadingIcon()
        setTrailingIcon()
        setCornerRadius()
        setState()
        setTypeButton()
        setStyle()
        setConfiguration()

        typeArray.recycle()


    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val desiredWidth = dpToPxInt(if (buttonType == ButtonType.NORMAL) 180 else 40)
        val desiredHeight = dpToPxInt(40)

        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)

        //Measure Width
        val width = when (widthMode) {
            MeasureSpec.EXACTLY -> { // number 50dp or match_parent
                //Must be this size
                widthSize
            }

            MeasureSpec.AT_MOST -> { // wrap_content
                //Can't be bigger than...
                min(desiredWidth.toDouble(), widthSize.toDouble()).toInt()
            }

            MeasureSpec.UNSPECIFIED -> { //
                //Be whatever you want
                desiredWidth
            }

            else -> {
                //Be whatever you want
                desiredWidth
            }
        }

        //Measure Height
        val height = when (heightMode) {
            MeasureSpec.EXACTLY -> { // number 50dp or match_parent
                //Must be this size
                heightSize
            }

            MeasureSpec.AT_MOST -> {// wrap_content
                //Can't be bigger than...
                min(desiredHeight.toDouble(), heightSize.toDouble()).toInt()
            }

            MeasureSpec.UNSPECIFIED -> {
                //Be whatever you want
                desiredHeight
            }

            else -> {
                //Be whatever you want
                desiredHeight
            }
        }


        // button
        measureChildren(
            MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY),
            MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY)
        )
        // cl
        setMeasuredDimension(width, height)
    }

    private fun setColor() {
        normalColor = myColors.normal
        disableColor = myColors.disable
        pressedColor = myColors.pressed
        pressedMaskColor = myColors.pressedMask
        tintEnableColor = myColors.tintEnable
        tintDisableColor = myColors.tintDisable
        borderColor = myColors.border
    }

    private fun clickListener() {
        binding.cl.setOnClickListener {
            if (::onClickListener.isInitialized) {
                onClickListener.invoke()
            }
        }
        binding.cl.setOnLongClickListener {
            if (::onLongClickListener.isInitialized) {
                onLongClickListener.invoke()
            }
            true
        }
        binding.cl.setOnTouchListener { _, event ->
            if (::onTouchListener.isInitialized) {
                onTouchListener.invoke(event)
            }
            false
        }
    }

    private fun getStateColorTint(tintEnable: Int? = tintEnableColor): ColorStateList {
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
    }

    private fun applyCorner(value: Int) {
        val corner = dpToPx(value)
        shape.cornerRadii =
            floatArrayOf(corner, corner, corner, corner, corner, corner, corner, corner)
        binding.mainLayout.background = stateListDrawable()
    }

    private fun getRippleColor(color: Int): Drawable {
        val outerRadii = FloatArray(8)
        Arrays.fill(
            outerRadii,
            ((cornerRadius as? CornerRadius)?.cornerRadiusId ?: (cornerRadius as? Int)
            ?: 0).toFloat()
        )
        val r = RoundRectShape(outerRadii, null, null)
        val shapeDrawable = ShapeDrawable(r)
        shapeDrawable.paint.color = color
        return shapeDrawable
    }

    private fun getPressedColorSelector(): ColorStateList {
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

    private fun setImageDrawableLeadingIcon(value: Drawable?) {
        binding.leadingIcon.setImageDrawable(value)
    }

    private fun setImageDrawableTrailingIcon(value: Drawable?) {
        binding.trailingIcon.setImageDrawable(value)
    }

    private fun handleText() {
        binding.text.text = text
    }

    private fun handleStyle() {
        setStyleColor()
        if (style == StyleButton.ELEVATED) {
            if (state == StateButton.DISABLE) {
                binding.mainLayout.elevation = dpToPx(0)
                binding.mainLayout.setMargin(0)
            } else {
                binding.mainLayout.elevation = dpToPx(2)
                binding.mainLayout.outlineProvider = ViewOutlineProvider.BACKGROUND
                binding.mainLayout.setMargin(2)

            }
        } else {
            binding.mainLayout.elevation = dpToPx(0)
            binding.mainLayout.setMargin(0)
        }
        if (state == StateButton.LOADING) {
            binding.mainLayout.setPaddingRelative(padding = 0)
        }

        binding.mainLayout.requestLayout()
    }

    private fun setStroke() {
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
    }

    private fun stateListDrawable(): StateListDrawable {

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

    private fun state() {
        setStyleColor()
        progressBar?.isVisible = state == StateButton.LOADING
        when (state) {
            StateButton.ENABLE -> {

                if (buttonType == ButtonType.NORMAL) {
                    binding.text.isVisible = true
                    binding.trailingIcon.isVisible = true
                }

                binding.leadingIcon.isVisible = true
                binding.text.isEnabled = true
                binding.mainLayout.isEnabled = true
                binding.trailingIcon.isEnabled = true
                binding.leadingIcon.isEnabled = true
                binding.cl.isEnabled = true

            }

            StateButton.DISABLE -> {

                if (buttonType == ButtonType.NORMAL) {
                    binding.text.isVisible = true
                    binding.trailingIcon.isVisible = true
                }

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

    private fun setStyleColor() {
        myColors = ColorsPalletButtonImp(context, configuration, style, state)
    }

    private fun setState() {
        stateId = typeArray.getInt(
            R.styleable.MyButton_stateButton,
            StateButton.ENABLE.stateId
        )
    }

    private fun setTypeButton() {
        buttonTypeId = typeArray.getInt(
            R.styleable.MyButton_buttonType,
            ButtonType.NORMAL.typeId
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

    fun setOnClickListener(event: () -> Unit) {
        this.onClickListener = event
    }

    fun setonLongClickListener(event: () -> Unit) {
        this.onLongClickListener = event
    }

    fun setonTouchListener(event: (motionEvent: MotionEvent?) -> Boolean) {
        this.onTouchListener = event
    }

}
