package com.example.composecalculator.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.composecalculator.ui.components.CalculatorButton
import com.example.composecalculator.ui.components.CalculatorButtonType
import com.example.composecalculator.ui.components.buttonList

@Composable
fun CalculatorScreen(
    viewModel: CalculatorViewModel = viewModel()
) {
    val state by viewModel.uiState.collectAsState()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.secondary
    ) {
        val textColor = Color(0xff212121)

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            Column {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp),
                    text = state.firstNumber + (state.operation?.symbol ?: "") + state.secondNumber,
                    style = TextStyle(
                        color = textColor,
                        fontSize = 48.sp,
                        fontWeight = FontWeight.Bold,
                        lineHeight = 48.sp,
                    ),
                    maxLines = 2
                )
                Spacer(modifier = Modifier.height(32.dp))
                LazyVerticalGrid(
                    modifier = Modifier
                        .clip(RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))
                        .background(MaterialTheme.colorScheme.primary)
                        .padding(8.dp),
                    columns = GridCells.Fixed(4),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    contentPadding = PaddingValues(16.dp)
                ) {
                    items(items = state.buttons) { button ->
                        CalculatorButton(
                            data = button,
                            textColor = textColor,
                            onClick = {
                                when (button.type) {
                                    CalculatorButtonType.Normal -> {
                                        if (button.symbol == ",") {
                                            viewModel.onAction(CalculatorAction.Decimal)
                                        } else {
                                            viewModel.onAction(
                                                CalculatorAction.Number(
                                                    button.symbol ?: "0"
                                                )
                                            )
                                        }
                                    }

                                    CalculatorButtonType.Action -> {
                                        when (button.symbol) {
                                            "+" -> viewModel.onAction(
                                                CalculatorAction.Operation(
                                                    CalculatorOperation.Add
                                                )
                                            )

                                            "-" -> viewModel.onAction(
                                                CalculatorAction.Operation(
                                                    CalculatorOperation.Subtract
                                                )
                                            )

                                            "×" -> viewModel.onAction(
                                                CalculatorAction.Operation(
                                                    CalculatorOperation.Multiply
                                                )
                                            )

                                            "÷" -> viewModel.onAction(
                                                CalculatorAction.Operation(
                                                    CalculatorOperation.Divide
                                                )
                                            )

                                            "%" -> viewModel.onAction(
                                                CalculatorAction.Operation(
                                                    CalculatorOperation.Percentage
                                                )
                                            )

                                            "=" -> viewModel.onAction(CalculatorAction.Calculate)
                                        }
                                    }

                                    CalculatorButtonType.Reset -> {
                                        viewModel.onAction(CalculatorAction.Clear)
                                    }

                                    CalculatorButtonType.Delete -> {
                                        viewModel.onAction(CalculatorAction.Delete)
                                    }
                                }
                            },
                        )
                    }
                }
            }
        }
    }
}