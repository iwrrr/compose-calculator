package com.example.composecalculator.ui.screen

import com.example.composecalculator.ui.components.CalculatorButtonData
import com.example.composecalculator.ui.components.buttonList

data class CalculatorState(
    val firstNumber: String = "",
    val secondNumber: String = "",
    val operation: CalculatorOperation? = null,
    val buttons: List<CalculatorButtonData> = buttonList,
)