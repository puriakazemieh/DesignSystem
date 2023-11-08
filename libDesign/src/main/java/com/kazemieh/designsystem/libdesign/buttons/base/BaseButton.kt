package com.kazemieh.designsystem.libdesign.buttons.base

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.content.res.TypedArray
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
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
import android.widget.FrameLayout
import com.kazemieh.designsystem.libdesign.R
import com.kazemieh.designsystem.libdesign.databinding.ButtonBinding
import com.kazemieh.designsystem.libdesign.util.dpToPx
import java.util.Arrays


@SuppressLint("ClickableViewAccessibility", "CustomViewStyleable")
abstract class BaseButton<ColorPallet> @JvmOverloads constructor(
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


    protected abstract var normalColor: Int
    protected abstract var pressedColor: Int
    protected abstract var pressedMaskColor: Int
    protected abstract var tintEnableColor: Int

    protected abstract var myColors: ColorPallet

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

    var cornerRadius: CornerRadius = CornerRadius.ROUND_100
        set(value) {
            field = value
            Log.d(TAG, "cornerRadius: $value ")
            applyCorner(value.cornerRadiusId)
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

        typeArray.recycle()
    }

    protected abstract fun invalidateLayout()
    protected abstract fun setStyleColor()
    abstract fun getStateColorTint(tintEnable: Int? = tintEnableColor): ColorStateList
    protected abstract fun stateListDrawable(): StateListDrawable

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

    protected open fun setImageDrawableLeadingIcon(value: Drawable?) {
        binding.leadingIcon.setImageDrawable(value)
    }

    protected open fun setImageDrawableTrailingIcon(value: Drawable?) {
        binding.trailingIcon.setImageDrawable(value)
    }

    protected open fun applyCorner(value: Int) {
        val corner = dpToPx(value)
        shape.cornerRadii =
            floatArrayOf(corner, corner, corner, corner, corner, corner, corner, corner)
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
        Arrays.fill(outerRadii, cornerRadius.cornerRadiusId.toFloat())
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
