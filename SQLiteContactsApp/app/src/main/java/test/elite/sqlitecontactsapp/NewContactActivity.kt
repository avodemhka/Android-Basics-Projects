package test.elite.sqlitecontactsapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class NewContactActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_new_contact)

        val editName = findViewById<EditText>(R.id.editName)

        val editPhone = findViewById<EditText>(R.id.editPhone)

        val btnSave = findViewById<Button>(R.id.btnSave)

        val db = DatabaseHelper(this)

        btnSave.setOnClickListener {

            val name = editName.text.toString()

            val phone = editPhone.text.toString()

            if (name.isNotEmpty() && phone.isNotEmpty()) {

                db.insertContact(name, phone)

                Toast.makeText(
                    this,
                    "Contact Added",
                    Toast.LENGTH_SHORT
                ).show()

                finish()
            }
        }
    }
}