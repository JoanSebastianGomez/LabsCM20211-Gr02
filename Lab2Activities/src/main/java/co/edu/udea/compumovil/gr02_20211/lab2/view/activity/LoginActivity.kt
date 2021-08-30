package co.edu.udea.compumovil.gr02_20211.lab2.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.findNavController
import co.edu.udea.compumovil.gr02_20211.lab2.R
import co.edu.udea.compumovil.gr02_20211.lab2.data.database.database.JapanDatabase
import co.edu.udea.compumovil.gr02_20211.lab2.data.database.entity.PlaceEntity
import co.edu.udea.compumovil.gr02_20211.lab2.data.database.entity.UserEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class LoginActivity : AppCompatActivity() {

    private val dataList = mutableListOf<UserEntity>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        findViewById<Button>(R.id.register).setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
            finish()
        }
        findViewById<Button>(R.id.login).setOnClickListener {
            insert(
                findViewById<EditText>(R.id.email).text.toString(),
                findViewById<EditText>(R.id.password).text.toString(),
            )


        }
    }

    private fun insert(user: String, password: String) = GlobalScope.launch {
        dataList.apply {
            addAll(
                JapanDatabase.getInstance(this@LoginActivity).userDao().getUserLogin(user, password)
            )
        }
        if (dataList.size > 0) {

            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
            finish()
        } else {
            withContext(Dispatchers.Main) {
                Toast.makeText(this@LoginActivity, "no logueo", Toast.LENGTH_SHORT).show()

            }
        }
    }
}