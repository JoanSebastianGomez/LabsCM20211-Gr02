package co.edu.udea.compumovil.gr02_20211.lab2

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class PlacesActivity : AppCompatActivity(), InterfaceClickAdapter {

    private lateinit var rv: RecyclerView
    private val dataList = mutableListOf<PlacesListObject>()
    private lateinit var listAdapter: ListAdapter
    private lateinit var register: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_places)
        findViewById<Button>(R.id.logout).setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
        dataList.apply {
            add(
                PlacesListObject(
                    "https://revistatravelmanager.com/wp-content/uploads/2019/05/parque-de-ueno-Tokio.jpg",
                    "Tokyo Tower",
                    "This is the tokyo tower",
                    "4",
                    PlaceDetailsObject(
                        "Information Tokyo Tower",
                        "Japan, Tokyo",
                        "28°C",
                        "Recommended +10"
                    )
                )
            )
            add(
                PlacesListObject(
                    "https://static.dw.com/image/56308193_303.jpg",
                    "Tokyo Skytree",
                    "This is the tokyo tower",
                    "5",
                    PlaceDetailsObject(
                        "Information Tokyo Skytree",
                        "Japan, Tokyo",
                        "29°C",
                        "Recommended +11"
                    )
                )
            )
            add(
                PlacesListObject(
                    "https://www.lavanguardia.com/files/content_image_mobile_filter/uploads/2019/08/05/5fa5314d64bee.jpeg",
                    "Tokyo Station",
                    "This is the Tokyo Station",
                    "4",
                    PlaceDetailsObject(
                        "Information Tokyo Station",
                        "Japan, Tokyo",
                        "30°C",
                        "Recommended +12"
                    )
                )
            )
            add(
                PlacesListObject(
                    "https://okdiario.com/img/2019/08/31/curiosidades-sobre-japon-655x368.jpg",
                    "Akihabara",
                    "This is the Akihabara",
                    "5",
                    PlaceDetailsObject(
                        "Information Akihabara",
                        "Japan, Tokyo",
                        "31°C",
                        "Recommended +13"
                    )
                )
            )
            add(
                PlacesListObject(
                    "https://www.caracteristicas.co/wp-content/uploads/2017/07/japon-7-e1571188430646.jpg",
                    "Shibuya",
                    "This is the Shibuya",
                    "3",
                    PlaceDetailsObject(
                        "Information Shibuya",
                        "Japan, Tokyo",
                        "32°C",
                        "Recommended +14"
                    )
                )
            )
            add(
                PlacesListObject(
                    "https://www.japonalternativo.com/wp-content/uploads/2020/02/preparativos-antes-de-viajar-a-Jap%C3%B3n.jpg",
                    "Harajuku",
                    "This is the Harajuku",
                    "3",
                    PlaceDetailsObject(
                        "Information Harajuku",
                        "Japan, Tokyo",
                        "33°C",
                        "Recommended +15"
                    )
                )
            )
        }
        listAdapter = ListAdapter(dataList, this)
        rv = findViewById<RecyclerView>(R.id.placesList).apply {
            adapter = listAdapter
            layoutManager = LinearLayoutManager(context)
        }

        register = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) {
                (it.data?.getSerializableExtra("data") as PlacesListObject).let { item ->
                    dataList.add(item)
                    listAdapter.notifyItemInserted(dataList.lastIndex)
                }
            }
        }

        findViewById<FloatingActionButton>(R.id.fabAdd).setOnClickListener {
            register.launch(Intent(this, AddPlaceActivity::class.java))
        }
    }

    override fun onPlaceClick(position: Int) {
        val intent = Intent(this, DetailsActivity::class.java).apply {
            putExtra("extras", dataList[position])
        }
        startActivity(intent)
    }


}