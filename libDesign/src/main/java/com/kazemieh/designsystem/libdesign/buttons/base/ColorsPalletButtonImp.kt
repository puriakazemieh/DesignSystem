package com.kazemieh.designsystem.libdesign.buttons.base

import android.content.Context
import com.kazemieh.designsystem.libdesign.buttons.normal.small.ConfigurationSmallButton
import com.kazemieh.designsystem.libdesign.buttons.normal.small.StateSmallButton
import com.kazemieh.designsystem.libdesign.buttons.normal.small.StyleSmallButton
import com.kazemieh.designsystem.libdesign.color.ColorsPalletImp
import com.kazemieh.designsystem.libdesign.util.setAlpha

class ColorsPalletButtonImp(
    context: Context,
    configuration: ConfigurationSmallButton,
    style: StyleSmallButton,
    state: StateSmallButton
) : ColorsPalletImp(context) {


     val normal: Int = if (configuration == ConfigurationSmallButton.PRIMARY) {
        when (style) {
            StyleSmallButton.FIELD -> {
                primary
            }

            StyleSmallButton.OUTLINE, StyleSmallButton.STANDARD -> {
                surface
            }

            StyleSmallButton.TONAL -> {
                secondaryContainer
            }

            StyleSmallButton.ELEVATED -> {
                surfaceTint
            }
        }

    } else {
        when (style) {
            StyleSmallButton.FIELD -> {
                error
            }

            StyleSmallButton.OUTLINE, StyleSmallButton.STANDARD -> {
                surface
            }

            StyleSmallButton.TONAL -> {
                errorContainer
            }

            StyleSmallButton.ELEVATED -> {
                surfaceTint
            }
        }

    }


     val pressed: Int = if (configuration == ConfigurationSmallButton.PRIMARY) {
        when (style) {
            StyleSmallButton.FIELD -> {
                onPrimary
            }

            StyleSmallButton.OUTLINE, StyleSmallButton.STANDARD, StyleSmallButton.ELEVATED, StyleSmallButton.TONAL -> {
                primary
            }
        }

    } else {
        when (style) {
            StyleSmallButton.FIELD -> {
                onError
            }

            StyleSmallButton.OUTLINE, StyleSmallButton.STANDARD, StyleSmallButton.ELEVATED, StyleSmallButton.TONAL -> {
                error
            }
        }

    }


     val pressedMask: Int = (if (configuration == ConfigurationSmallButton.PRIMARY) {
        when (style) {
            StyleSmallButton.FIELD -> {
                onPrimary
            }

            StyleSmallButton.OUTLINE, StyleSmallButton.STANDARD, StyleSmallButton.ELEVATED, StyleSmallButton.TONAL -> {
                primary
            }
        }

    } else {
        when (style) {
            StyleSmallButton.FIELD -> {
                onError
            }

            StyleSmallButton.OUTLINE, StyleSmallButton.STANDARD, StyleSmallButton.ELEVATED, StyleSmallButton.TONAL -> {
                error
            }
        }

    }).setAlpha(alpha12)


     val disable: Int? = (when (style) {
        StyleSmallButton.FIELD, StyleSmallButton.OUTLINE, StyleSmallButton.ELEVATED, StyleSmallButton.TONAL -> {
            onSurface.setAlpha(alpha12)
        }

        StyleSmallButton.STANDARD -> {
            null
        }

    })


     val tintEnable: Int = if (configuration == ConfigurationSmallButton.PRIMARY) {
        when (style) {
            StyleSmallButton.FIELD -> {
                onPrimary
            }

            StyleSmallButton.OUTLINE, StyleSmallButton.STANDARD, StyleSmallButton.ELEVATED, StyleSmallButton.TONAL -> {
                primary
            }

        }

    } else {
        when (style) {
            StyleSmallButton.FIELD -> {
                onError
            }

            StyleSmallButton.OUTLINE, StyleSmallButton.STANDARD, StyleSmallButton.ELEVATED, StyleSmallButton.TONAL -> {
                error
            }

        }

    }


     val tintDisable: Int = onSurface.setAlpha(alpha38)


     val border: Int = if (configuration == ConfigurationSmallButton.PRIMARY) {
        if (state == StateSmallButton.DISABLE) {
            when (style) {
                StyleSmallButton.TONAL -> {
                    outline
                }

                StyleSmallButton.FIELD, StyleSmallButton.STANDARD, StyleSmallButton.ELEVATED, StyleSmallButton.OUTLINE -> {
                    surface
                }

            }
        } else {
            when (style) {
                StyleSmallButton.OUTLINE -> {
                    outline
                }

                StyleSmallButton.TONAL -> {
                    primary
                }

                StyleSmallButton.FIELD, StyleSmallButton.STANDARD, StyleSmallButton.ELEVATED -> {
                    surface
                }

            }
        }


    } else {
        if (state == StateSmallButton.DISABLE) {
            when (style) {
                StyleSmallButton.TONAL -> {
                    outline
                }

                StyleSmallButton.FIELD, StyleSmallButton.STANDARD, StyleSmallButton.ELEVATED, StyleSmallButton.OUTLINE -> {
                    surface
                }

            }
        } else {
            when (style) {
                StyleSmallButton.OUTLINE -> {
                    outline
                }

                StyleSmallButton.TONAL -> {
                    error
                }

                StyleSmallButton.FIELD, StyleSmallButton.STANDARD, StyleSmallButton.ELEVATED -> {
                    surface
                }

            }
        }
    }


}