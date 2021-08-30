package co.edu.udea.compumovil.gr02_20211.lab2.data.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import co.edu.udea.compumovil.gr02_20211.lab2.R
import co.edu.udea.compumovil.gr02_20211.lab2.data.database.entity.PlaceEntity
import co.edu.udea.compumovil.gr02_20211.lab2.data.models.PlacesListObject
import com.squareup.picasso.Picasso

class ListAdapter(
    private val listener: InterfaceClickAdapter
) : RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    private val data = mutableListOf<PlaceEntity>()

    class ViewHolder(
        itemView: View,
        private val listener: InterfaceClickAdapter
    ) : RecyclerView.ViewHolder(itemView) {
        val buttonDelete: ImageButton = itemView.findViewById(R.id.buttonDelete)
        val itemImage: ImageView = itemView.findViewById(R.id.thumbnail)
        val itemName: TextView = itemView.findViewById(R.id.name)
        val itemScore: TextView = itemView.findViewById(R.id.score)
        val itemDescription: TextView = itemView.findViewById(R.id.description)

        init {
            buttonDelete.setOnClickListener { listener.delete(adapterPosition) }
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

    fun updateData(newData: Collection<PlaceEntity>) {
        data.apply {
            clear()
            addAll(newData)
        }
        notifyDataSetChanged()
    }

}

interface InterfaceClickAdapter {
    fun onPlaceClick(position: Int)
    fun delete(position: Int)
}