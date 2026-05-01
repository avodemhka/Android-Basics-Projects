package test.elite.calculator

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class CalculatorActivity : AppCompatActivity() {

    private var firstNumber = 0.0
    private var operator = ""

    private lateinit var textResult: TextView
    private lateinit var textExpression: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)

        textResult = findViewById(R.id.textResult)
        textExpression = findViewById(R.id.textExpression)

        val btn0: Button = findViewById(R.id.btn0)
        val btn1: Button = findViewById(R.id.btn1)
        val btn2: Button = findViewById(R.id.btn2)
        val btn3: Button = findViewById(R.id.btn3)
        val btn4: Button = findViewById(R.id.btn4)
        val btn5: Button = findViewById(R.id.btn5)
        val btn6: Button = findViewById(R.id.btn6)
        val btn7: Button = findViewById(R.id.btn7)
        val btn8: Button = findViewById(R.id.btn8)
        val btn9: Button = findViewById(R.id.btn9)

        val btnPlus: Button = findViewById(R.id.btnPlus)
        val btnMinus: Button = findViewById(R.id.btnMinus)
        val btnMultiply: Button = findViewById(R.id.btnMultiply)
        val btnDivide: Button = findViewById(R.id.btnDivide)
        val btnEqual: Button = findViewById(R.id.btnEqual)

        val numberButtons = listOf(btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9)

        for (button in numberButtons) {
            button.setOnClickListener {
                val current = textResult.text.toString()
                textResult.text = if (current == "0") {
                    button.text.toString()
                } else {
                    current + button.text.toString()
                }
            }
        }

        btnPlus.setOnClickListener { chooseOperator("+") }
        btnMinus.setOnClickListener { chooseOperator("-") }
        btnMultiply.setOnClickListener { chooseOperator("*") }
        btnDivide.setOnClickListener { chooseOperator("/") }

        btnEqual.setOnClickListener {
            val secondText = textResult.text.toString()
            if (secondText.isEmpty()) return@setOnClickListener

            val secondNumber = secondText.toDouble()
            val result = when (operator) {
                "+" -> firstNumber + secondNumber
                "-" -> firstNumber - secondNumber
                "*" -> firstNumber * secondNumber
                "/" -> firstNumber / secondNumber
                else -> secondNumber
            }

            textExpression.text = "$firstNumber $operator $secondNumber ="
            textResult.text = result.toString()
        }
    }

    private fun chooseOperator(op: String) {
        val currentText = textResult.text.toString()
        if (currentText.isEmpty()) return

        firstNumber = currentText.toDouble()
        operator = op
        textExpression.text = "$firstNumber $operator"
        textResult.text = ""
    }
}