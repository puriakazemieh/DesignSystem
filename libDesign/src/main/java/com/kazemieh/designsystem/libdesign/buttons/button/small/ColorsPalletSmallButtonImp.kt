package com.kazemieh.designsystem.libdesign.buttons.button.small

import android.content.Context
import com.kazemieh.designsystem.libdesign.buttons.button.ColorsPalletButtonImp
import com.kazemieh.designsystem.libdesign.color.ColorsInt.Primary
import com.kazemieh.designsystem.libdesign.util.setAlpha

class ColorsPalletSmallButtonImp(
    context: Context,
    configuration: ConfigurationSmallButton,
    style: StyleSmallButton,
    state: StateSmallButton
) : ColorsPalletButtonImp(context) {


    override val normal: Int = if (configuration == ConfigurationSmallButton.PRIMARY) {
        when (style) {
            StyleSmallButton.FIELD -> {
                Primary
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


    override val pressed: Int = if (configuration == ConfigurationSmallButton.PRIMARY) {
        when (style) {
            StyleSmallButton.FIELD -> {
                onPrimary
            }

            StyleSmallButton.OUTLINE, StyleSmallButton.STANDARD, StyleSmallButton.ELEVATED, StyleSmallButton.TONAL -> {
                Primary
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


    override val pressedMask: Int = (if (configuration == ConfigurationSmallButton.PRIMARY) {
        when (style) {
            StyleSmallButton.FIELD -> {
                onPrimary
            }

            StyleSmallButton.OUTLINE, StyleSmallButton.STANDARD, StyleSmallButton.ELEVATED, StyleSmallButton.TONAL -> {
                Primary
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


    override val disable: Int? = (when (style) {
        StyleSmallButton.FIELD, StyleSmallButton.OUTLINE, StyleSmallButton.ELEVATED, StyleSmallButton.TONAL -> {
            onSurface.setAlpha(alpha12)
        }

        StyleSmallButton.STANDARD -> {
            null
        }

    })


    override val tintEnable: Int = if (configuration == ConfigurationSmallButton.PRIMARY) {
        when (style) {
            StyleSmallButton.FIELD -> {
                onPrimary
            }

            StyleSmallButton.OUTLINE, StyleSmallButton.STANDARD, StyleSmallButton.ELEVATED, StyleSmallButton.TONAL -> {
                Primary
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


    override val tintDisable: Int = onSurface.setAlpha(alpha38)


    override val border: Int = if (configuration == ConfigurationSmallButton.PRIMARY) {
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
                    Primary
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