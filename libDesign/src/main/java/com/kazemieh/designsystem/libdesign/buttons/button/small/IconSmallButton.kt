package com.kazemieh.designsystem.libdesign.buttons.button.small

import android.content.Context
import android.util.AttributeSet
import android.view.ViewOutlineProvider
import androidx.core.view.isVisible
import com.kazemieh.designsystem.libdesign.buttons.base.CornerRadius
import com.kazemieh.designsystem.libdesign.util.dpToPx
import com.kazemieh.designsystem.libdesign.util.dpToPxInt
import com.kazemieh.designsystem.libdesign.util.layoutParam
import com.kazemieh.designsystem.libdesign.util.marginLayoutParams
import com.kazemieh.designsystem.libdesign.util.setMargin
import com.kazemieh.designsystem.libdesign.util.updateLayoutParam
import com.kazemieh.designsystem.libdesign.util.setPaddingRelative

class IconSmallButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet?,
    defStyleAttr: Int = 0,
) : SmallButton(
    context, attrs, defStyleAttr
) {

    init {
        binding.text.isVisible = false
        binding.text.text = ""
        binding.trailingIcon.isVisible = false

        binding.leadingIcon.marginLayoutParams {
            width = dpToPxInt(24)
            height = dpToPxInt(24)
            marginEnd = dpToPxInt(0)
        }

        binding.text.layoutParam {
            height = dpToPxInt(0)
            width = dpToPxInt(0)
        }

        cornerRadius = CornerRadius.ROUND_100

        binding.mainLayout.setMargin(4)
        binding.text.setMargin(0)
        binding.mainLayout.setPaddingRelative(padding = 0)

    }

    override fun invalidateLayout() {
        super.invalidateLayout()
        if (style == StyleSmallButton.ELEVATED) {

            if (state == StateSmallButton.DISABLE) {
                binding.mainLayout.elevation = dpToPx(0)
                binding.mainLayout.setMargin(0)

                binding.mainLayout.updateLayoutParam {
                    height = dpToPxInt(40)
                    width = dpToPxInt(40)
                }
                binding.cl.updateLayoutParam {
                    height = dpToPxInt(40)
                    width = dpToPxInt(40)
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
                    width = dpToPxInt(36)
                }
                binding.cl.updateLayoutParam {
                    height = dpToPxInt(40)
                    width = dpToPxInt(40)
                }
            }

        } else {
            binding.mainLayout.elevation = dpToPx(0)
            binding.mainLayout.setMargin(0)

            binding.mainLayout.updateLayoutParam {
                height = dpToPxInt(40)
                width = dpToPxInt(40)
            }
            binding.cl.updateLayoutParam {
                height = dpToPxInt(40)
                width = dpToPxInt(40)
            }

        }
    }
}