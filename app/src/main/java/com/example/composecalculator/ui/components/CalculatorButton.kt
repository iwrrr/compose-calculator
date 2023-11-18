package com.example.composecalculator.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composecalculator.ui.theme.Cyan
import com.example.composecalculator.ui.theme.Red

@Composable
fun CalculatorButton(
    modifier: Modifier = Modifier,
    data: CalculatorButtonData,
    textColor: Color,
    onClick: () -> Unit,
) {
    Surface(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .fillMaxHeight()
            .aspectRatio(1f)
            .clickable { onClick() },
    ) {
        Box(
            modifier = modifier
                .background(MaterialTheme.colorScheme.secondary),
            contentAlignment = Alignment.Center
        ) {
            val contentColor = when (data.type) {
                CalculatorButtonType.Normal -> textColor
                CalculatorButtonType.Action -> Cyan
                CalculatorButtonType.Reset -> Cyan
                CalculatorButtonType.Delete -> Red
            }

            data.symbol?.let {
                Text(
                    text = data.symbol,
                    color = contentColor,
                    fontWeight = FontWeight.Bold,
                    fontSize = if (data.type == CalculatorButtonType.Action) 25.sp else 20.sp,
                )
            }

            data.icon?.let {
                Icon(
                    modifier = Modifier.size(24.dp),
                    imageVector = it,
                    contentDescription = null,
                    tint = contentColor
                )
            }
        }
    }
}