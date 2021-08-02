package co.edu.udea.compumovil.gr02_20211.lab2

import java.io.Serializable

data class PlaceDetailsObject(
    val information: String,
    val location: String,
    val temperature: String,
    val recommendations: String
):Serializable