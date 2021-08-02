package co.edu.udea.compumovil.gr02_20211.lab2

import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.os.Bundle
import android.widget.Button


class LoginActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        findViewById<Button>(R.id.register).setOnClickListener{
            startActivity(Intent(this, RegisterActivity::class.java))
            finish()
        }
        findViewById<Button>(R.id.login).setOnClickListener{
            startActivity(Intent(this, PlacesActivity::class.java))
            finish()
        }
    }
}