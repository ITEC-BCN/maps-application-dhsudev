package com.example.mapsapp.ui.screens.main.markers

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.mapsapp.ui.navigation.Destinations

@Composable
fun DetailMarkerScreen(markerId: String){
    Column (modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally){
        Text("This is the Detail Marker Screen")
        Text("Parameters received, id: $markerId")
    }
}