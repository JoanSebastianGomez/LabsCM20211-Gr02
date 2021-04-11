package co.edu.udea.compumovil.gr02_20211.lab1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import co.edu.udea.compumovil.gr02_20211.lab1.databinding.ActivityContactDataBinding

class ContactDataActivity : AppCompatActivity() {

    private lateinit var binding: ActivityContactDataBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnNext.setOnClickListener { nextClick() }
    }

    private fun nextClick() {
        if (validate()) {
            with(binding) {
                val response = "Teléfono: ${etTelephone.text} - Dirección: ${etAddress.text} " +
                        "- País: ${etCountry.text} - Ciudad: ${etCity.text}"
                Log.d("MAIN", response)
            }
            finish()
        } else Toast.makeText(
            this,
            "Hay campos sin validar",
            Toast.LENGTH_LONG
        ).show()
    }

    private fun validate() = with(binding) {
        etTelephone.valid() && etCountry.valid() && etMail.valid()
    }

    private fun EditText.valid() : Boolean = this.text.isNotBlank()

}