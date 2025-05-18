package com.example.mapsapp.data

data class Marker(
    var markerId:String,
    var owner: String = "none",
    var long: Double,
    var lat: Double,
    var title: String,
    val desc : String,
    val image : String
)