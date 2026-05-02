package test.elite.intentapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val editText = findViewById<EditText>(R.id.editText)
        val btnSend = findViewById<Button>(R.id.btnSend)
        val btnClear = findViewById<Button>(R.id.btnClear)
        val textCount = findViewById<TextView>(R.id.textCount)

        btnClear.setOnClickListener {
            editText.text.clear()
            textCount.text = "Length: 0"
        }

        btnSend.setOnClickListener {
            val originalText = editText.text.toString()

            if (originalText.isEmpty()) {
                editText.error = "Enter text"
                return@setOnClickListener
            }

            val cleanedText = originalText.replace(Regex("[^a-zA-Z]"), "")
            val invalidCount = originalText.length - cleanedText.length

            textCount.text = "Length: ${cleanedText.length}"

            val intent = Intent()
            intent.putExtra("cleaned", cleanedText)
            intent.putExtra("invalidCount", invalidCount)

            setResult(RESULT_OK, intent)
            finish()
        }
    }
}