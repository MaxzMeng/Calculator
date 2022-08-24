package me.maxzmeng.calculator.ui.button

import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import me.maxzmeng.calculator.ui.theme.*

val operationUiModel = ButtonUiModel(
    textColor = Color.White,
    backgroundColor = Yellow,
    pressedColor = RippleYellow,
    selectedColor = Color.White
)

val commonUiModel = ButtonUiModel(
    textColor = Color.White,
    backgroundColor = DarkGray,
    pressedColor = RippleGray,
)

val zeroUiModel = ButtonUiModel(
    textColor = Color.White,
    backgroundColor = DarkGray,
    pressedColor = RippleGray,
    textAlign = Alignment.Start,
    weight = 2
)

val immediateCalculateUiModel = ButtonUiModel(
    textColor = Color.Black,
    backgroundColor = Gray,
    pressedColor = Color.White,
)