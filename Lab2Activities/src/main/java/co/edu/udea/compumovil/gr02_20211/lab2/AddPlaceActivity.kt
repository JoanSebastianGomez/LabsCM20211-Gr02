package co.edu.udea.compumovil.gr02_20211.lab2

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class AddPlaceActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_place)

        findViewById<Button>(R.id.btnAdd).setOnClickListener {
            // Crear el nuevo item
            //val intent = Intent().putExtra("data", // aqui va el nuevo item)
            //setResult(Activity.RESULT_OK, intent)
        }
        findViewById<Button>(R.id.btnCancel).setOnClickListener { finish() }
    }
}
