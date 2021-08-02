package co.edu.udea.compumovil.gr02_20211.lab2

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.lang.Exception

class ListAdapter(
    private val data: MutableList<PlacesListObject>,
    private val listener: InterfaceClickAdapter
) : RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    class ViewHolder(
        itemView: View,
        private val listener: InterfaceClickAdapter
    ) : RecyclerView.ViewHolder(itemView) {

        val itemImage: ImageView = itemView.findViewById(R.id.thumbnail)
        val itemName: TextView = itemView.findViewById(R.id.name)
        val itemScore: TextView = itemView.findViewById(R.id.score)
        val itemDescription: TextView = itemView.findViewById(R.id.description)

        init {
            itemView.setOnClickListener { listener.onPlaceClick(adapterPosition) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_row, parent, false)
        return ViewHolder(itemView, listener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.apply {
            itemName.text = item.name
            itemScore.text = "Score: ${item.score}"
            itemDescription.text = item.description
            Picasso.get().load(item.image).error(R.drawable.ic_launcher_background).into(itemImage)
        }
    }

    override fun getItemCount() = data.size

}

interface InterfaceClickAdapter {
    fun onPlaceClick(position: Int)
}