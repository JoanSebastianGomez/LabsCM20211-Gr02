package co.edu.udea.compumovil.gr02_20211.lab2.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.edu.udea.compumovil.gr02_20211.lab2.R
import co.edu.udea.compumovil.gr02_20211.lab2.data.adapter.InterfaceClickAdapter
import co.edu.udea.compumovil.gr02_20211.lab2.data.adapter.ListAdapter
import co.edu.udea.compumovil.gr02_20211.lab2.data.database.database.JapanDatabase
import co.edu.udea.compumovil.gr02_20211.lab2.data.database.entity.PlaceEntity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PlacesFragment : Fragment(), InterfaceClickAdapter {

    private val dataList = mutableListOf<PlaceEntity>()
    private lateinit var listAdapter: ListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_places, container, false)

    override fun onResume() {
        super.onResume()
        getData()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.settings).setOnClickListener {
            view.findNavController().navigate(R.id.action_placesFragment_to_preferenceFragment)
        }
//        dataList.apply {
//            add(
//                PlacesListObject(
//                    "https://revistatravelmanager.com/wp-content/uploads/2019/05/parque-de-ueno-Tokio.jpg",
//                    "Tokyo Tower",
//                    "This is the tokyo tower",
//                    "4",
//                    PlaceDetailsObject(
//                        "Information Tokyo Tower",
//                        "Japan, Tokyo",
//                        "28°C",
//                        "Recommended +10"
//                    )
//                )
//            )
//            add(
//                PlacesListObject(
//                    "https://www.nippon.com/es/ncommon/contents/views/98455/98455.jpg",
//                    "Tokyo Skytree",
//                    "This is the tokyo tower",
//                    "5",
//                    PlaceDetailsObject(
//                        "Information Tokyo Skytree",
//                        "Japan, Tokyo",
//                        "29°C",
//                        "Recommended +11"
//                    )
//                )
//            )
//            add(
//                PlacesListObject(
//                    "https://www.lavanguardia.com/files/content_image_mobile_filter/uploads/2019/08/05/5fa5314d64bee.jpeg",
//                    "Tokyo Station",
//                    "This is the Tokyo Station",
//                    "4",
//                    PlaceDetailsObject(
//                        "Information Tokyo Station",
//                        "Japan, Tokyo",
//                        "30°C",
//                        "Recommended +12"
//                    )
//                )
//            )
//            add(
//                PlacesListObject(
//                    "https://okdiario.com/img/2019/08/31/curiosidades-sobre-japon-655x368.jpg",
//                    "Akihabara",
//                    "This is the Akihabara",
//                    "5",
//                    PlaceDetailsObject(
//                        "Information Akihabara",
//                        "Japan, Tokyo",
//                        "31°C",
//                        "Recommended +13"
//                    )
//                )
//            )
//            add(
//                PlacesListObject(
//                    "https://www.caracteristicas.co/wp-content/uploads/2017/07/japon-7-e1571188430646.jpg",
//                    "Shibuya",
//                    "This is the Shibuya",
//                    "3",
//                    PlaceDetailsObject(
//                        "Information Shibuya",
//                        "Japan, Tokyo",
//                        "32°C",
//                        "Recommended +14"
//                    )
//                )
//            )
//            add(
//                PlacesListObject(
//                    "https://www.japonalternativo.com/wp-content/uploads/2020/02/preparativos-antes-de-viajar-a-Jap%C3%B3n.jpg",
//                    "Harajuku",
//                    "This is the Harajuku",
//                    "3",
//                    PlaceDetailsObject(
//                        "Information Harajuku",
//                        "Japan, Tokyo",
//                        "33°C",
//                        "Recommended +15"
//                    )
//                )
//            )
//        }
        listAdapter = ListAdapter(this)
        view.findViewById<RecyclerView>(R.id.placesList).apply {
            adapter = listAdapter
            layoutManager = LinearLayoutManager(context)
        }

        view.findViewById<FloatingActionButton>(R.id.fabAdd).setOnClickListener {
            findNavController().navigate(R.id.addPlacesFragment)
        }
    }

    override fun onPlaceClick(position: Int) {
        val bundle = Bundle().apply {
            putSerializable("extras", dataList[position])
        }
        findNavController().navigate(R.id.detailsFragment, bundle)
    }

    private fun getData() = GlobalScope.launch {
        val dao = JapanDatabase.getInstance(requireContext()).placeDao()
        dataList.apply {
            clear()
            addAll(dao.getEntities())
        }
        withContext(Dispatchers.Main) { listAdapter.updateData(dataList) }
    }
}