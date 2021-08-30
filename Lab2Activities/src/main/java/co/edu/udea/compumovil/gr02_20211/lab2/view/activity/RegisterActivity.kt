package co.edu.udea.compumovil.gr02_20211.lab2.view.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import co.edu.udea.compumovil.gr02_20211.lab2.R
import co.edu.udea.compumovil.gr02_20211.lab2.data.database.database.JapanDatabase
import co.edu.udea.compumovil.gr02_20211.lab2.data.database.entity.UserEntity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        findViewById<Button>(R.id.register).setOnClickListener {
            insert(
                UserEntity(
                    findViewById<EditText>(R.id.username).text.toString(),
                    findViewById<EditText>(R.id.email).text.toString(),
                    findViewById<EditText>(R.id.password).text.toString(),
                )
            )
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
    private fun insert(entry: UserEntity) = GlobalScope.launch {
        JapanDatabase.getInstance(this@RegisterActivity).userDao().insert(entry)
    }
}