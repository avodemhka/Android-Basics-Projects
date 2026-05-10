package test.elite.sqlitecontactsapp

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, "ContactsDB", null, 1) {

    override fun onCreate(db: SQLiteDatabase) {

        db.execSQL(
            "CREATE TABLE contacts (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "name TEXT, " +
                    "phone TEXT)"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

        db.execSQL("DROP TABLE IF EXISTS contacts")

        onCreate(db)
    }

    fun insertContact(name: String, phone: String) {

        val db = writableDatabase

        val values = ContentValues()

        values.put("name", name)
        values.put("phone", phone)

        db.insert("contacts", null, values)

        db.close()
    }

    fun getAllNames(): ArrayList<String> {

        val names = ArrayList<String>()

        val db = readableDatabase

        val cursor = db.rawQuery(
            "SELECT name FROM contacts",
            null
        )

        if (cursor.moveToFirst()) {

            do {

                names.add(cursor.getString(0))

            } while (cursor.moveToNext())
        }

        cursor.close()
        db.close()

        return names
    }

    fun getPhoneByName(name: String): String {

        var phone = ""

        val db = readableDatabase

        val cursor = db.rawQuery(
            "SELECT phone FROM contacts WHERE name = ?",
            arrayOf(name)
        )

        if (cursor.moveToFirst()) {

            phone = cursor.getString(0)
        }

        cursor.close()
        db.close()

        return phone
    }

    fun deleteContact(name: String) {

        val db = writableDatabase

        db.delete(
            "contacts",
            "name = ?",
            arrayOf(name)
        )

        db.close()
    }

    fun updateContact(
        oldName: String,
        newName: String,
        newPhone: String
    ) {

        val db = writableDatabase

        val values = ContentValues()

        values.put("name", newName)
        values.put("phone", newPhone)

        db.update(
            "contacts",
            values,
            "name = ?",
            arrayOf(oldName)
        )

        db.close()
    }
}