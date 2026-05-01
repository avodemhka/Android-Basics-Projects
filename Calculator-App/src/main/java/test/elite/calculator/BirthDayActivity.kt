package test.elite.calculator

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.time.LocalDate
import java.time.temporal.ChronoUnit

class BirthDayActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_birthday)

        val editDay = findViewById<EditText>(R.id.editDay)
        val editMonth = findViewById<EditText>(R.id.editMonth)
        val editYear = findViewById<EditText>(R.id.editYear)
        val btnCalculate = findViewById<Button>(R.id.btnCalculate)
        val textResult = findViewById<TextView>(R.id.textRes)

        btnCalculate.setOnClickListener {

            val dayText = editDay.text.toString()
            val monthText = editMonth.text.toString()
            val yearText = editYear.text.toString()

            if (dayText.isEmpty() || monthText.isEmpty() || yearText.isEmpty()) {
                textResult.text = "Please fill all fields"
                return@setOnClickListener
            }

            val day = dayText.toInt()
            val month = monthText.toInt()
            val year = yearText.toInt()

            if (!isValidDate(day, month, year)) {
                textResult.text = "Invalid date"
                return@setOnClickListener
            }

            val birthDate = LocalDate.of(year, month, day)
            val today = LocalDate.now()
            val daysPassed = ChronoUnit.DAYS.between(birthDate, today)

            textResult.text = "Days passed: $daysPassed"
        }
    }

    private fun isValidDate(day: Int, month: Int, year: Int): Boolean {

        val currentYear = LocalDate.now().year

        if (year < 1 || year > currentYear) return false
        if (month < 1 || month > 12) return false

        val maxDay = when (month) {
            1, 3, 5, 7, 8, 10, 12 -> 31
            4, 6, 9, 11 -> 30
            2 -> if (year % 4 == 0) 29 else 28
            else -> 0
        }

        return day in 1..maxDay
    }
}