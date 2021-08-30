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

    override fun delete(position: Int) {
        var toDelete= dataList[position]
        delete(toDelete)
    }

    private fun delete(place: PlaceEntity) = GlobalScope.launch {
        context?.let { JapanDatabase.getInstance(it).placeDao().deleteEntity(place) }
        getData()
    }
}