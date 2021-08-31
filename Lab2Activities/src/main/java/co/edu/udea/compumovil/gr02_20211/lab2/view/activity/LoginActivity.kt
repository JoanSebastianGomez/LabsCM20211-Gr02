package co.edu.udea.compumovil.gr02_20211.lab2.view.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import co.edu.udea.compumovil.gr02_20211.lab2.R
import co.edu.udea.compumovil.gr02_20211.lab2.data.database.database.JapanDatabase
import co.edu.udea.compumovil.gr02_20211.lab2.data.database.entity.PlaceEntity
import co.edu.udea.compumovil.gr02_20211.lab2.data.database.entity.UserEntity
import co.edu.udea.compumovil.gr02_20211.lab2.data.preferences.EnvironmentPreferences
import co.edu.udea.compumovil.gr02_20211.lab2.data.preferences.Preferences.FIRST_TIME_LOGGED
import co.edu.udea.compumovil.gr02_20211.lab2.data.preferences.Preferences.USER_LOGGED_IN
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginActivity : AppCompatActivity() {

    private val dataList = mutableListOf<UserEntity>()
    private lateinit var preferences : EnvironmentPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        preferences = EnvironmentPreferences(this)
        findViewById<Button>(R.id.register).setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
            finish()
        }
        findViewById<Button>(R.id.login).setOnClickListener {
            getInfo(
                findViewById<EditText>(R.id.email).text.toString(),
                findViewById<EditText>(R.id.password).text.toString(),
            )
        }

        preferences.apply {
            if (!isPreferenceActive(FIRST_TIME_LOGGED)) {
                setPreference(FIRST_TIME_LOGGED, true)
                insertDummyData()
            }

            if (isPreferenceActive(USER_LOGGED_IN)) goToMainActivity()
        }
    }

    private fun insertDummyData() {
        insert(
            PlaceEntity(
                "Tokyo Tower",
                "Is a tower in tokyo, japan",
                "Mide 333m",
                "Tokyo, Japan",
                "15°",
                "Subir a lo mas alto",
                "https://revistatravelmanager.com/wp-content/uploads/2019/05/parque-de-ueno-Tokio.jpg",
                "4"
            )
        )
        insert(
            PlaceEntity(
                "Tokyo Skytree",
                "Is the highest tower tokyo, japan",
                "Mide 634m",
                "Tokyo, Japan",
                "15°",
                "Subir al mirador",
                "https://www.japonalternativo.com/wp-content/uploads/2020/02/preparativos-antes-de-viajar-a-Jap%C3%B3n.jpg",
                "4"
            )
        )
        insert(
            PlaceEntity(
                "Akihabara",
                "Is a street in tokyo, japan",
                "Is the center of anime and videogames",
                "Tokyo, Japan",
                "15°",
                "Go to the SEGA building",
                "https://okdiario.com/img/2019/08/31/curiosidades-sobre-japon-655x368.jpg",
                "4"
            )
        )
        insert(
            PlaceEntity(
                "Tokyo Station",
                "Is a train station in tokyo, japan",
                "Opened in 1914",
                "Tokyo, Japan",
                "15°",
                "Try bullet train",
                "https://revistatravelmanager.com/wp-content/uploads/2019/05/parque-de-ueno-Tokio.jpg",
                "4"
            )
        )
        insert(
            PlaceEntity(
                "Harajuku",
                "Is a street in tokyo, japan",
                "Is a center of culture and fashion",
                "Tokyo, Japan",
                "15°",
                "Dress the best you can",
                "https://www.japonalternativo.com/wp-content/uploads/2020/02/preparativos-antes-de-viajar-a-Jap%C3%B3n.jpg",
                "4"
            )
        )
    }

    private fun getInfo(user: String, password: String) = GlobalScope.launch {
        dataList.apply {
            addAll(
                JapanDatabase.getInstance(this@LoginActivity).userDao().getUserLogin(user, password)
            )
        }
        if (dataList.size > 0) goToMainActivity()
        else withContext(Dispatchers.Main) {
            Toast.makeText(this@LoginActivity, "no logueo", Toast.LENGTH_SHORT).show()
        }
    }

    private fun goToMainActivity() {
        startActivity(Intent(this@LoginActivity, MainActivity::class.java))
        finish()
    }

    private fun insert(entry: PlaceEntity) = GlobalScope.launch {
        JapanDatabase.getInstance(this@LoginActivity).placeDao().insert(entry)
    }
}