package co.edu.udea.compumovil.gr02_20211.lab1

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.DatePicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import co.edu.udea.compumovil.gr02_20211.lab1.databinding.PersonalDataActivityBinding
import java.util.*

class PersonalDataActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener {
    private lateinit var binding: PersonalDataActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = PersonalDataActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setListener()
    }

    private fun setListener() {
        with(binding) {
            etBirthDate.setOnClickListener { showDatePickerDialog() }
            btnNext.setOnClickListener { nextClick() }
        }
    }

    private fun nextClick() {
        if (validate()) {
            with(binding) {
                val sex = when (rgSex.checkedRadioButtonId) {
                    R.id.rbMan -> "Hombre"
                    R.id.rbWoman -> "Mujer"
                    else -> "No Binario"
                }

                val studies = if (spStudies.selectedItemPosition == 0) "Sin estudios"
                else spStudies.selectedItem

                val response = "Nombre: ${etName.text} - Apellido: ${etLastName.text} " +
                        "- Sexo: $sex - Fecha de nacimiento: ${etBirthDate.text} - " +
                        "Estudios: $studies"
                Log.d("MAIN", response)
                startActivity(Intent(
                    this@PersonalDataActivity,
                    ContactDataActivity::class.java)
                )
            }
        } else Toast.makeText(
            this@PersonalDataActivity,
            "Hay campos sin validar",
            Toast.LENGTH_LONG
        ).show()
    }

    private fun validate() = with(binding) {
        etName.text.isNotBlank()
                && etLastName.text.isNotBlank()
                && etBirthDate.text.isNotBlank()
    }

    private fun showDatePickerDialog() {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        val newFragment = DatePickerDialog(this, this, year, month, day)
        newFragment.show()
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        val date = "$dayOfMonth/$month/$year"
        binding.etBirthDate.setText(date)
    }

}

