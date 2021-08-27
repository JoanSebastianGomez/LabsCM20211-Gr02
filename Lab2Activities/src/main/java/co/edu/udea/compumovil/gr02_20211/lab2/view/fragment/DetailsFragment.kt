package co.edu.udea.compumovil.gr02_20211.lab2.view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import co.edu.udea.compumovil.gr02_20211.lab2.R
import co.edu.udea.compumovil.gr02_20211.lab2.data.database.entity.PlaceEntity


class DetailsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = layoutInflater.inflate(R.layout.fragment_details, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?): Unit = with(view) {
        super.onViewCreated(view, savedInstanceState)

        val itemDetails = arguments?.getSerializable("extras") as PlaceEntity
        with(itemDetails) {
            findViewById<TextView>(R.id.name).text = itemDetails.name
            findViewById<TextView>(R.id.information).text = information
            findViewById<TextView>(R.id.location).text = location
            findViewById<TextView>(R.id.temperature).text = temperature
            findViewById<TextView>(R.id.recommendations).text = recommendations
        }

        findViewById<Button>(R.id.btnBack).setOnClickListener { findNavController().navigateUp() }
    }

}