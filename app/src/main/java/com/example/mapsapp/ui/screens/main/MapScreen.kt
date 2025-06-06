package com.example.mapsapp.ui.screens.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun MapScreen(onLongClick: (String) -> Unit) {
    Column(Modifier.fillMaxSize().padding(top = 100.dp, start = 16.dp, end = 16.dp)) {

        val itb = LatLng(41.4534225, 2.1837151)
        val cameraPositionState = rememberCameraPositionState {
            position = CameraPosition.fromLatLngZoom(itb, 17f)
        }
        GoogleMap(
            Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState,
            onMapLongClick = {  latLng ->
                onLongClick(latLng.toString())
            }
        ){
            Marker(
                state = MarkerState(position = itb),
                title = "ITB",
                snippet = "Marker at ITB"
            )
        }
    }
}
