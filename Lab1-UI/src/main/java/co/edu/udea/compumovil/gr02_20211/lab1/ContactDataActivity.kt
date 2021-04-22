package co.edu.udea.compumovil.gr02_20211.lab1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Toast
import co.edu.udea.compumovil.gr02_20211.lab1.databinding.ActivityContactDataBinding
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import org.json.JSONObject
import java.util.ArrayList

class ContactDataActivity : AppCompatActivity() {

    private lateinit var binding: ActivityContactDataBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactDataBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setListeners()
        binding.btnNext.setOnClickListener { nextClick() }
        val countries = resources.getStringArray(R.array.countries_array)
        searchCities()
        ArrayAdapter(this, android.R.layout.simple_list_item_1, countries).also { adapter ->
            binding.etCountry.setAdapter(adapter)
        }
    }

    private fun setListeners() {
        binding.etCountry.setOnFocusChangeListener { _, hasfocus ->
            if (!hasfocus) searchCities()
        }
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

    private fun EditText.valid(): Boolean = this.text.isNotBlank()

    private fun searchCities() {
        val url = "https://countriesnow.space/api/v0.1/countries/cities"
        val queue = Volley.newRequestQueue(this)
        val json = JSONObject();
        json.put("country", binding.etCountry.text.toString())
        val request = JsonObjectRequest(
            Request.Method.POST,
            url, json,
            { response ->

                val dataResponse = response.getJSONArray("data")
                val cities = mutableListOf<String>()
                for (i in 0 until dataResponse.length()) {
                    cities.add(dataResponse[i] as String)
                }

                ArrayAdapter(this, android.R.layout.simple_list_item_1, cities).also { adapter ->
                    binding.etCity.setAdapter(adapter)
                }
            },
            { Log.d("MAIN", "salio mal") })
        queue.add(request)
    }
}