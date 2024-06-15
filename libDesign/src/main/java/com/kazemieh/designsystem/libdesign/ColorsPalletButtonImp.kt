package com.kazemieh.designsystem.libdesign

import android.content.Context
import com.kazemieh.designsystem.libdesign.color.ColorsPalletImp
import com.kazemieh.designsystem.libdesign.util.setAlpha

class ColorsPalletButtonImp(
    context: Context,
    configuration: ConfigurationButton,
    style: StyleButton,
    state: StateButton
) : ColorsPalletImp(context) {


    val normal: Int = if (configuration == ConfigurationButton.PRIMARY) {
        when (style) {
            StyleButton.FIELD -> {
                primary
            }

            StyleButton.OUTLINE, StyleButton.STANDARD -> {
                surface
            }

            StyleButton.TONAL -> {
                secondaryContainer
            }

            StyleButton.ELEVATED -> {
                surfaceTint
            }
        }

    } else {
        when (style) {
            StyleButton.FIELD -> {
                error
            }

            StyleButton.OUTLINE, StyleButton.STANDARD -> {
                surface
            }

            StyleButton.TONAL -> {
                errorContainer
            }

            StyleButton.ELEVATED -> {
                surfaceTint
            }
        }

    }


    val pressed: Int = if (configuration == ConfigurationButton.PRIMARY) {
        when (style) {
            StyleButton.FIELD -> {
                onPrimary
            }

            StyleButton.OUTLINE, StyleButton.STANDARD, StyleButton.ELEVATED, StyleButton.TONAL -> {
                primary
            }
        }

    } else {
        when (style) {
            StyleButton.FIELD -> {
                onError
            }

            StyleButton.OUTLINE, StyleButton.STANDARD, StyleButton.ELEVATED, StyleButton.TONAL -> {
                error
            }
        }

    }


    val pressedMask: Int = (if (configuration == ConfigurationButton.PRIMARY) {
        when (style) {
            StyleButton.FIELD -> {
                onPrimary
            }

            StyleButton.OUTLINE, StyleButton.STANDARD, StyleButton.ELEVATED, StyleButton.TONAL -> {
                primary
            }
        }

    } else {
        when (style) {
            StyleButton.FIELD -> {
                onError
            }

            StyleButton.OUTLINE, StyleButton.STANDARD, StyleButton.ELEVATED, StyleButton.TONAL -> {
                error
            }
        }

    }).setAlpha(alpha12)


    val disable: Int? = (when (style) {
        StyleButton.FIELD, StyleButton.OUTLINE, StyleButton.ELEVATED, StyleButton.TONAL -> {
            onSurface.setAlpha(alpha12)
        }

        StyleButton.STANDARD -> {
            null
        }

    })


    val tintEnable: Int = if (configuration == ConfigurationButton.PRIMARY) {
        when (style) {
            StyleButton.FIELD -> {
                onPrimary
            }

            StyleButton.OUTLINE, StyleButton.STANDARD, StyleButton.ELEVATED, StyleButton.TONAL -> {
                primary
            }

        }

    } else {
        when (style) {
            StyleButton.FIELD -> {
                onError
            }

            StyleButton.OUTLINE, StyleButton.STANDARD, StyleButton.ELEVATED, StyleButton.TONAL -> {
                error
            }

        }

    }


    val tintDisable: Int = onSurface.setAlpha(alpha38)


    val border: Int = if (configuration == ConfigurationButton.PRIMARY) {
        if (state == StateButton.DISABLE) {
            when (style) {
                StyleButton.TONAL -> {
                    outline
                }

                StyleButton.FIELD, StyleButton.STANDARD, StyleButton.ELEVATED, StyleButton.OUTLINE -> {
                    surface
                }

            }
        } else {
            when (style) {
                StyleButton.OUTLINE -> {
                    outline
                }

                StyleButton.TONAL -> {
                    primary
                }

                StyleButton.FIELD, StyleButton.STANDARD, StyleButton.ELEVATED -> {
                    surface
                }

            }
        }


    } else {
        if (state == StateButton.DISABLE) {
            when (style) {
                StyleButton.TONAL -> {
                    outline
                }

                StyleButton.FIELD, StyleButton.STANDARD, StyleButton.ELEVATED, StyleButton.OUTLINE -> {
                    surface
                }

            }
        } else {
            when (style) {
                StyleButton.OUTLINE -> {
                    outline
                }

                StyleButton.TONAL -> {
                    error
                }

                StyleButton.FIELD, StyleButton.STANDARD, StyleButton.ELEVATED -> {
                    surface
                }

            }
        }
    }


}