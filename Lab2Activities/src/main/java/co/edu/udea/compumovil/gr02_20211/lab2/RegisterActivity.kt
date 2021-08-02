package co.edu.udea.compumovil.gr02_20211.lab2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        findViewById<Button>(R.id.register).setOnClickListener{
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}