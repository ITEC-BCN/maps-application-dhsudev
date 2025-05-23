package com.example.mapsapp.data

import kotlinx.serialization.Serializable

@Serializable
data class Marker(
    var id: Int,
    var owner: String? = null,
    var long: Double,
    var lat: Double,
    var title: String,
    val desc : String,
    val image : String? = null
)