package com.mfstdio.calculator

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mfstdio.calculator.ui.theme.CalculatorTheme

@Composable
fun CalculatorButton(
    modifier: Modifier = Modifier,
    value: String,
    onClick: () -> Unit,
    color: Color = MaterialTheme.colorScheme.inversePrimary
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(containerColor = color),
        shape = RoundedCornerShape(6.dp)
    ) {
        Text(text = value, fontSize = 48.sp, fontWeight = FontWeight.Bold)
    }
}

@Preview
@Composable
private fun CalculatorButtonPreview() {
    CalculatorTheme {
        CalculatorButton(value = "5", onClick = { println(5) })
    }
}