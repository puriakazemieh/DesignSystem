package com.kazemieh.designsystem.libdesign.buttons.button.small

enum class ConfigurationSmallButton(val configurationId: Int) {
    PRIMARY(0), ERROR(1)
}

enum class StyleSmallButton(val styleId: Int) {
    FIELD(0), OUTLINE(1), STANDARD(2), ELEVATED(3), TONAL(4)
}

enum class StateSmallButton(val stateId: Int) {
    ENABLE(0), DISABLE(1), LOADING(2)
}