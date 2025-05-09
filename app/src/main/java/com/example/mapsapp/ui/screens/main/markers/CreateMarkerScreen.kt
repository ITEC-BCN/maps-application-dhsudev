package com.example.mapsapp.ui.screens.main.markers

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.mapsapp.ui.navigation.Destinations
import com.google.android.gms.maps.model.LatLng

@Composable
fun CreateMarkerScreen(latLng: LatLng){
    Column (modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally){
        Text("This is the Create marker Screen")
        Text("Parameters received, lat: ${latLng.latitude}, lng: ${latLng.longitude}")
    }
}