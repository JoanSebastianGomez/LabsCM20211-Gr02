package co.edu.udea.compumovil.gr02_20211.lab2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val itemDetails = intent.extras?.getSerializable("extras") as PlacesListObject
        with(itemDetails.details) {
            findViewById<TextView>(R.id.name).text = itemDetails.name
            findViewById<TextView>(R.id.information).text = information
            findViewById<TextView>(R.id.location).text = location
            findViewById<TextView>(R.id.temperature).text = temperature
            findViewById<TextView>(R.id.recommendations).text = recommendations
        }

        findViewById<Button>(R.id.btnBack).setOnClickListener { finish() }
    }
}