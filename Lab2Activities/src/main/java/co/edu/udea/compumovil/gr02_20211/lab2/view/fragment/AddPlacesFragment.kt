package co.edu.udea.compumovil.gr02_20211.lab2.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import co.edu.udea.compumovil.gr02_20211.lab2.R
import co.edu.udea.compumovil.gr02_20211.lab2.data.database.database.JapanDatabase
import co.edu.udea.compumovil.gr02_20211.lab2.data.database.entity.PlaceEntity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AddPlacesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = layoutInflater.inflate(R.layout.fragment_add_place, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(view) {
        super.onViewCreated(view, savedInstanceState)
        findViewById<Button>(R.id.btnAdd).setOnClickListener {
            insert(
                PlaceEntity(
                    "Angieeeeeeeee 5",
                    "Angie descr",
                    "Angie Info",
                    "Angie location",
                    "Angie temp",
                    "Angie recomm",
                    "https://revistatravelmanager.com/wp-content/uploads/2019/05/parque-de-ueno-Tokio.jpg",
                    "5"
                )
            )
            goUp()
        }
        findViewById<Button>(R.id.btnCancel).setOnClickListener { goUp() }
    }

    private fun goUp() {
        findNavController().navigateUp()
    }

    private fun insert(entry: PlaceEntity) = GlobalScope.launch {
        context?.let { JapanDatabase.getInstance(it).placeDao().insert(entry) }
    }
}
