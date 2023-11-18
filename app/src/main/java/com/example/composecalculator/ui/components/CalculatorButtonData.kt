package com.example.composecalculator.ui.components

import androidx.compose.ui.graphics.vector.ImageVector

data class CalculatorButtonData(
    val symbol: String? = null,
    val type: CalculatorButtonType,
    val icon: ImageVector? = null
)

val buttonList = listOf(
    CalculatorButtonData(symbol = "C", type = CalculatorButtonType.Reset),
    CalculatorButtonData(symbol = "%", type = CalculatorButtonType.Action),
    CalculatorButtonData(symbol = "Del", type = CalculatorButtonType.Delete),
    CalculatorButtonData(symbol = "÷", type = CalculatorButtonType.Action),

    CalculatorButtonData(symbol = "7", type = CalculatorButtonType.Normal),
    CalculatorButtonData(symbol = "8", type = CalculatorButtonType.Normal),
    CalculatorButtonData(symbol = "9", type = CalculatorButtonType.Normal),

    CalculatorButtonData(symbol = "×", type = CalculatorButtonType.Action),

    CalculatorButtonData(symbol = "4", type = CalculatorButtonType.Normal),
    CalculatorButtonData(symbol = "5", type = CalculatorButtonType.Normal),
    CalculatorButtonData(symbol = "6", type = CalculatorButtonType.Normal),

    CalculatorButtonData(symbol = "-", type = CalculatorButtonType.Action),

    CalculatorButtonData(symbol = "1", type = CalculatorButtonType.Normal),
    CalculatorButtonData(symbol = "2", type = CalculatorButtonType.Normal),
    CalculatorButtonData(symbol = "3", type = CalculatorButtonType.Normal),

    CalculatorButtonData(symbol = "+", type = CalculatorButtonType.Action),

    CalculatorButtonData(symbol = "00", type = CalculatorButtonType.Normal),
    CalculatorButtonData(symbol = "0", type = CalculatorButtonType.Normal),
    CalculatorButtonData(symbol = ",", type = CalculatorButtonType.Normal),
    CalculatorButtonData(symbol = "=", type = CalculatorButtonType.Action),
)