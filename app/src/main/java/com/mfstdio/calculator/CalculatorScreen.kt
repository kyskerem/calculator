package com.mfstdio.calculator

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mfstdio.calculator.ui.theme.CalculatorTheme
import net.objecthunter.exp4j.ExpressionBuilder

@Composable
fun CalculatorScreen() {
    var input by remember { mutableStateOf("") }

    fun addToInput(value: String) {
        input += value
    }

    fun clearInput() {
        input = ""
    }

    fun calculateResult() {
        val result = try {
            val parsedResult = ExpressionBuilder(input).build().evaluate()
            if (parsedResult.isNaN()) {
                "Error"
            } else {
                parsedResult.toString()
            }
        } catch (e: Exception) {
            "Error"
        }
        input = result
    }

    fun deleteInput() {
        if (input.isNotEmpty()) input = input.dropLast(1)
    }

    fun onButtonClicked(value: String) {
        when (value) {
            "C" -> clearInput()
            "%" -> addToInput("%")
            "/" -> addToInput("/")
            "<" -> deleteInput()
            "*" -> addToInput("*")
            "7", "8", "9", "4", "5", "6", "1", "2", "3", "+", "-", ".", "0", "00" -> addToInput(
                value
            )

            "=" -> calculateResult()
        }
    }
    Column(modifier = Modifier.fillMaxSize()) {
        DisplayInput(input)
        ButtonGrid(
            buttonRows = listOf(
                listOf("C", "%", "/", "<"),
                listOf("7", "8", "9", "*"),
                listOf("4", "5", "6", "-"),
                listOf("1", "2", "3", "+"),
                listOf(".", "0", "00", "=")
            ),
            onButtonClicked = ::onButtonClicked
        )
    }
}

@Composable
private fun DisplayInput(input: String) {
    Text(
        text = input,
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .background(Color.DarkGray)
            .padding(8.dp)
            .padding(vertical = 60.dp),
        textAlign = TextAlign.Right,
        fontSize = 32.sp,
        fontWeight = FontWeight.Bold,
        color = Color.White,
    )
}

@Composable
private fun ButtonGrid(
    buttonRows: List<List<String>>,
    onButtonClicked: (String) -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Spacer(modifier = Modifier.padding(vertical = 20.dp))
        buttonRows.forEach { row ->
            Row {
                row.forEach { buttonValue ->
                    CalculatorButton(
                        value = buttonValue,
                        onClick = { onButtonClicked(buttonValue) },
                        modifier = Modifier
                            .weight(if (buttonValue == "00") 1.2f else 1f)
                            .padding(4.dp),
                        )

                }
            }
            Spacer(modifier = Modifier.padding(vertical = 10.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    CalculatorTheme {
        CalculatorScreen()
    }
}
