package com.example.mapsapp.ui.screens.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun MapScreen(onLongClick: (LatLng) -> Unit) {
    Column(Modifier.fillMaxSize()) {
        Text("This is the Map Screen")

        val itb = LatLng(41.4534225, 2.1837151)
        val cameraPositionState = rememberCameraPositionState {
            position = CameraPosition.fromLatLngZoom(itb, 17f)
        }
        GoogleMap(
            Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState,
            onMapClick = {  latLng ->
                onLongClick(latLng)
            }
        )
    }
}
