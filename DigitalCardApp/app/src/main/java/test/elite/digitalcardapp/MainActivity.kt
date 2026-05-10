package test.elite.digitalcardapp

import android.os.Bundle
import android.widget.TextView
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val btnContact = findViewById<TextView>(R.id.btnContact)

        btnContact.setOnClickListener {

            Toast.makeText(
                this,
                "İletişime Geçildi!",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}