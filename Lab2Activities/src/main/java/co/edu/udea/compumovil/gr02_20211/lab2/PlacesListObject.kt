package co.edu.udea.compumovil.gr02_20211.lab2

import java.io.Serializable

data class PlacesListObject(
    val image: String,
    val name: String,
    val description: String,
    val score: String,
    val details: PlaceDetailsObject
):Serializable