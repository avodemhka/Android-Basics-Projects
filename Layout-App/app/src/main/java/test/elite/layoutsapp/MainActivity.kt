package test.elite.layoutsapp

import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var imageView: ImageView
    private lateinit var btnLeft: ImageButton
    private lateinit var btnRight: ImageButton
    private lateinit var textTitle: TextView
    private lateinit var textPlate: TextView
    private lateinit var textDesc: TextView

    private var index = 0

    private val images = arrayOf(
        R.drawable.city1,
        R.drawable.city2,
        R.drawable.city3
    )

    private val titles = arrayOf(
        R.string.city1_title,
        R.string.city2_title,
        R.string.city3_title
    )

    private val plates = arrayOf(
        R.string.city1_plate,
        R.string.city2_plate,
        R.string.city3_plate
    )

    private val descriptions = arrayOf(
        R.string.city1_desc,
        R.string.city2_desc,
        R.string.city3_desc
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageView = findViewById(R.id.imageView)
        btnLeft = findViewById(R.id.btnLeft)
        btnRight = findViewById(R.id.btnRight)
        textTitle = findViewById(R.id.textTitle)
        textPlate = findViewById(R.id.textPlate)
        textDesc = findViewById(R.id.textDesc)

        updateUI()

        btnRight.setOnClickListener {
            if (index < images.size - 1) {
                index++
                updateUI()
            }
        }

        btnLeft.setOnClickListener {
            if (index > 0) {
                index--
                updateUI()
            }
        }
    }

    private fun updateUI() {
        imageView.setImageResource(images[index])
        textTitle.setText(titles[index])
        textPlate.setText(plates[index])
        textDesc.setText(descriptions[index])

        btnLeft.visibility = if (index == 0) View.INVISIBLE else View.VISIBLE
        btnRight.visibility = if (index == images.size - 1) View.INVISIBLE else View.VISIBLE
    }
}