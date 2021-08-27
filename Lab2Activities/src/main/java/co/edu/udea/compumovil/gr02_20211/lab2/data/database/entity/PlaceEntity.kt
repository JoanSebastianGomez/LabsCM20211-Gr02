package co.edu.udea.compumovil.gr02_20211.lab2.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class PlaceEntity(
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "information")
    val information: String,
    @ColumnInfo(name = "location")
    val location: String,
    @ColumnInfo(name = "temperature")
    val temperature: String,
    @ColumnInfo(name = "recommendations")
    val recommendations: String,
    @ColumnInfo(name = "image")
    val image: String,
    @ColumnInfo(name = "score")
    val score: String
) : Serializable {
    @PrimaryKey(autoGenerate = true)
    var uid: Int = 0
}