package com.example.composecalculator.ui.screen

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CalculatorViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(CalculatorState())
    val uiState = _uiState.asStateFlow()

    fun onAction(action: CalculatorAction) {
        when (action) {
            is CalculatorAction.Number -> enterNumber(action.number)
            is CalculatorAction.Delete -> delete()
            is CalculatorAction.Clear -> clear()
            is CalculatorAction.Operation -> enterOperation(action.operation)
            is CalculatorAction.Decimal -> enterDecimal()
            is CalculatorAction.Calculate -> calculate()
        }
    }

    private fun enterNumber(number: String) {
        val state = _uiState.value
        val input = number.toInt()

        if (state.operation == null) {
            if (state.firstNumber.length >= MAX_NUM_LENGTH) return
            _uiState.update { it.copy(firstNumber = state.firstNumber + input) }
            return
        }

        if (state.operation == CalculatorOperation.Percentage) return

        if (state.secondNumber.length >= MAX_NUM_LENGTH) return
        _uiState.update { it.copy(secondNumber = state.secondNumber + input) }
    }

    private fun enterDecimal() {
        val state = _uiState.value

        if (state.operation == null && !state.firstNumber.contains(",") && state.firstNumber.isNotBlank()) {
            _uiState.update { it.copy(firstNumber = state.firstNumber + ",") }
            return
        } else if (!state.secondNumber.contains(",") && state.secondNumber.isNotBlank()) {
            _uiState.update { it.copy(secondNumber = state.secondNumber + ",") }
        }
    }

    private fun enterOperation(operation: CalculatorOperation) {
        if (_uiState.value.firstNumber.isNotBlank()) {
            _uiState.update { it.copy(operation = operation) }
        }
    }

    private fun clear() {
        _uiState.update { it.copy(firstNumber = "", secondNumber = "", operation = null) }
    }

    private fun delete() {
        when {
            _uiState.value.secondNumber.isNotBlank() -> {
                _uiState.update { it.copy(secondNumber = _uiState.value.secondNumber.dropLast(1)) }
            }

            _uiState.value.operation != null -> {
                _uiState.update { it.copy(operation = null) }
            }

            _uiState.value.firstNumber.isNotBlank() -> {
                _uiState.update { it.copy(firstNumber = _uiState.value.firstNumber.dropLast(1)) }
            }
        }
    }

    private fun calculate() {
        val number1 = if (_uiState.value.firstNumber.contains(",")) {
            _uiState.value.firstNumber.replace(",", ".").toBigDecimalOrNull()
        } else {
            _uiState.value.firstNumber.toBigDecimalOrNull()
        }

        val number2 = if (_uiState.value.secondNumber.contains(",")) {
            _uiState.value.secondNumber.replace(",", ".").toBigDecimalOrNull()
        } else {
            _uiState.value.secondNumber.toBigDecimalOrNull()
        }

        if (number1 == null) return

        val result = when (_uiState.value.operation) {
            is CalculatorOperation.Add -> number1 + (number2 ?: (0).toBigDecimal())
            is CalculatorOperation.Divide -> number1.divide(number2)
            is CalculatorOperation.Multiply -> number1 * (number2 ?: (0).toBigDecimal())
            is CalculatorOperation.Subtract -> number1 - (number2 ?: (0).toBigDecimal())
            is CalculatorOperation.Percentage -> number1.divide((100).toBigDecimal())
            null -> return
        }

        _uiState.update {
            it.copy(
                firstNumber = result.toString().replace(".", ",").take(15),
                secondNumber = "",
                operation = null,
            )
        }
    }

    companion object {
        const val MAX_NUM_LENGTH = 8
    }
}