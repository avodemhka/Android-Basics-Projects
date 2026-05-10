package test.elite.sqlitecontactsapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    lateinit var listContacts: ListView
    lateinit var db: DatabaseHelper

    var selectedName = ""
    var selectedPhone = ""

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        listContacts = findViewById(R.id.listContacts)

        db = DatabaseHelper(this)

        loadContacts()

        listContacts.setOnItemClickListener { _, _, position, _ ->

            val names = db.getAllNames()

            selectedName = names[position]

            selectedPhone = db.getPhoneByName(selectedName)

            Toast.makeText(
                this,
                "Selected: $selectedName\nPhone: $selectedPhone",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun onResume() {

        super.onResume()

        loadContacts()
    }

    private fun loadContacts() {

        val names = db.getAllNames()

        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            names
        )

        listContacts.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.option_menu, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {

            R.id.menuNewContact -> {

                val intent = Intent(
                    this,
                    NewContactActivity::class.java
                )

                startActivity(intent)
            }

            R.id.menuDelete -> {

                if (selectedName.isNotEmpty()) {

                    db.deleteContact(selectedName)

                    Toast.makeText(
                        this,
                        "Contact Deleted",
                        Toast.LENGTH_SHORT
                    ).show()

                    loadContacts()
                }
            }

            R.id.menuCall -> {

                if (selectedPhone.isNotEmpty()) {

                    val intent = Intent(
                        Intent.ACTION_DIAL,
                        Uri.parse("tel:$selectedPhone")
                    )

                    startActivity(intent)
                }
            }

            R.id.menuUpdate -> {

                if (selectedName.isNotEmpty()) {

                    val intent = Intent(
                        this,
                        UpdateContactActivity::class.java
                    )

                    intent.putExtra("name", selectedName)
                    intent.putExtra("phone", selectedPhone)

                    startActivity(intent)
                }
            }

            R.id.menuExit -> {

                finish()
            }
        }

        return true
    }
}