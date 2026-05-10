package test.elite.sqlitecontactsapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class UpdateContactActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_update_contact)

        val editName = findViewById<EditText>(R.id.editUpdateName)

        val editPhone = findViewById<EditText>(R.id.editUpdatePhone)

        val btnUpdate = findViewById<Button>(R.id.btnUpdate)

        val oldName = intent.getStringExtra("name").toString()

        val oldPhone = intent.getStringExtra("phone").toString()

        editName.setText(oldName)

        editPhone.setText(oldPhone)

        val db = DatabaseHelper(this)

        btnUpdate.setOnClickListener {

            val newName = editName.text.toString()

            val newPhone = editPhone.text.toString()

            db.updateContact(
                oldName,
                newName,
                newPhone
            )

            Toast.makeText(
                this,
                "Contact Updated",
                Toast.LENGTH_SHORT
            ).show()

            finish()
        }
    }
}