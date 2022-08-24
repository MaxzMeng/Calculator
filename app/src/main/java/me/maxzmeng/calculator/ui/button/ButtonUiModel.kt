package me.maxzmeng.calculator.ui.button

import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign

data class ButtonUiModel(
    val textColor: Color,
    val textAlign: Alignment.Horizontal = Alignment.CenterHorizontally,
    val backgroundColor: Color,
    val disabledBackGroundColor: Color? = null,
    val pressedColor: Color,
    val selectedColor: Color? = null,
    val enabled: Boolean = true,
    val weight: Int = 1,
)