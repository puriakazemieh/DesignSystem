package com.kazemieh.designsystem.libdesign.buttons.base

enum class CornerRadius(val cornerRadiusId: Int) {
    ROUND_8(8), ROUND_100(100)
}

enum class ConfigurationButton(val configurationId: Int) {
    PRIMARY(0), ERROR(1)
}

enum class StyleButton(val styleId: Int) {
    FIELD(0), OUTLINE(1), STANDARD(2), ELEVATED(3), TONAL(4)
}

enum class StateButton(val stateId: Int) {
    ENABLE(0), DISABLE(1), LOADING(2)
}