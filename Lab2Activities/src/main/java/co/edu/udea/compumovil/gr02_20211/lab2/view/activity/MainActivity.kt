package co.edu.udea.compumovil.gr02_20211.lab2.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import co.edu.udea.compumovil.gr02_20211.lab2.R
import co.edu.udea.compumovil.gr02_20211.lab2.data.preferences.EnvironmentPreferences
import co.edu.udea.compumovil.gr02_20211.lab2.data.preferences.Preferences.USER_LOGGED_IN

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        EnvironmentPreferences(this).setPreference(USER_LOGGED_IN, true)
    }
}