package test.elite.jsonschoolapp

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONObject
import java.net.URL

class MainActivity : AppCompatActivity() {

    lateinit var spinnerTeachers: Spinner
    lateinit var listCourses: ListView

    val teacherNames = ArrayList<String>()

    val teacherCourses = HashMap<String, ArrayList<String>>()

    val courseDetails = HashMap<String, String>()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        spinnerTeachers = findViewById(R.id.spinnerTeachers)

        listCourses = findViewById(R.id.listCourses)

        Thread {

            try {

                val jsonText = URL(
                    "https://raw.githubusercontent.com/yasinor/Mobil_Ders/main/school.json"
                ).readText()

                val jsonObject = JSONObject(jsonText)

                val teachersArray =
                    jsonObject.getJSONArray("OgretimElemanlari")

                val lessonsArray =
                    jsonObject.getJSONArray("Dersler")

                val teacherIdMap = HashMap<Int, String>()

                for (i in 0 until teachersArray.length()) {

                    val teacher =
                        teachersArray.getJSONObject(i)

                    val name =
                        teacher.getString("adi")

                    val sicil =
                        teacher.getInt("sicil")

                    teacherNames.add(name)

                    teacherIdMap[sicil] = name

                    teacherCourses[name] = ArrayList()
                }

                for (i in 0 until lessonsArray.length()) {

                    val lesson =
                        lessonsArray.getJSONObject(i)

                    val code =
                        lesson.getString("Kodu")

                    val name =
                        lesson.getString("Adi")

                    val credit =
                        lesson.getInt("Kredisi")

                    val teacherId =
                        lesson.getInt("OgretmenSicil")

                    val teacherName =
                        teacherIdMap[teacherId]

                    if (teacherName != null) {

                        teacherCourses[teacherName]?.add(name)

                        courseDetails[name] =
                            "Code: $code\n" +
                                    "Course: $name\n" +
                                    "Credit: $credit"
                    }
                }

                runOnUiThread {

                    val spinnerAdapter = ArrayAdapter(
                        this,
                        android.R.layout.simple_spinner_dropdown_item,
                        teacherNames
                    )

                    spinnerTeachers.adapter = spinnerAdapter

                    spinnerTeachers.onItemSelectedListener =
                        object :
                            android.widget.AdapterView.OnItemSelectedListener {

                            override fun onItemSelected(
                                parent: android.widget.AdapterView<*>?,
                                view: android.view.View?,
                                position: Int,
                                id: Long
                            ) {

                                val selectedTeacher =
                                    teacherNames[position]

                                val courses =
                                    teacherCourses[selectedTeacher]

                                val listAdapter = ArrayAdapter(
                                    this@MainActivity,
                                    android.R.layout.simple_list_item_1,
                                    courses!!
                                )

                                listCourses.adapter = listAdapter
                            }

                            override fun onNothingSelected(
                                parent: android.widget.AdapterView<*>?
                            ) {
                            }
                        }

                    listCourses.setOnItemClickListener {
                            _, _, position, _ ->

                        val selectedTeacher =
                            spinnerTeachers.selectedItem.toString()

                        val selectedCourse =
                            teacherCourses[selectedTeacher]?.get(position)

                        val details =
                            courseDetails[selectedCourse]

                        Toast.makeText(
                            this,
                            details,
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }

            } catch (e: Exception) {

                runOnUiThread {

                    Toast.makeText(
                        this,
                        e.message,
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

        }.start()
    }
}