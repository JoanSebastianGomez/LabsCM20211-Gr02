package co.edu.udea.compumovil.gr02_20211.lab1

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.DatePicker
import androidx.appcompat.app.AppCompatActivity
import co.edu.udea.compumovil.gr02_20211.lab1.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity(),DatePickerDialog.OnDateSetListener {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setListener()
    }

    private fun setListener(){
        with(binding){
            date.setOnClickListener{
                showDatePickerDialog()
            }
            btnNext.setOnClickListener {

            }


        }
    }

    private fun showDatePickerDialog() {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        val newFragment = DatePickerDialog(this, this,year,month,day)
        newFragment.show()
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        val date = "$dayOfMonth/$month/$year"
        binding.date.setText(date)
    }


}

