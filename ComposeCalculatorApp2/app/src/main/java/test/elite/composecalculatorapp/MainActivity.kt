package test.elite.composecalculatorapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContent {

            MaterialTheme {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFFDCDCDC)
                ) {

                    CalculatorScreen()
                }
            }
        }
    }
}

@Composable
fun CalculatorScreen() {

    var firstNumber by remember { mutableStateOf("") }

    var secondNumber by remember { mutableStateOf("") }

    var result by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        OutlinedTextField(
            value = firstNumber,
            onValueChange = {
                firstNumber = it
            },
            label = {
                Text("First Number")
            }
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = secondNumber,
            onValueChange = {
                secondNumber = it
            },
            label = {
                Text("Second Number")
            }
        )

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            CalculatorButton("+") {

                val a = firstNumber.toDoubleOrNull() ?: 0.0
                val b = secondNumber.toDoubleOrNull() ?: 0.0

                result = (a + b).toString()
            }

            CalculatorButton("-") {

                val a = firstNumber.toDoubleOrNull() ?: 0.0
                val b = secondNumber.toDoubleOrNull() ?: 0.0

                result = (a - b).toString()
            }

            CalculatorButton("*") {

                val a = firstNumber.toDoubleOrNull() ?: 0.0
                val b = secondNumber.toDoubleOrNull() ?: 0.0

                result = (a * b).toString()
            }

            CalculatorButton("/") {

                val a = firstNumber.toDoubleOrNull() ?: 0.0
                val b = secondNumber.toDoubleOrNull() ?: 0.0

                if (b != 0.0) {

                    result = (a / b).toString()

                } else {

                    result = "Cannot divide by zero"
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "Result = $result",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun CalculatorButton(
    symbol: String,
    onClick: () -> Unit
) {

    Button(
        onClick = onClick,
        shape = RoundedCornerShape(20.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF6A4FB3)
        ),
        modifier = Modifier.size(
            width = 70.dp,
            height = 50.dp
        )
    ) {

        Text(
            text = symbol,
            fontSize = 20.sp
        )
    }
}