package com.example.mapsapp.ui.screens.main.markers

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DetailMarkerScreen(
    markerId: String,
    navigateBack: () -> Unit
){
    Column (
        modifier = Modifier.fillMaxSize().padding(top = 100.dp, start = 16.dp, end = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text("This is the Detail Marker Screen")
        Text("Parameters received, id: $markerId")
        Button(onClick = { navigateBack() }) { Text("Go back to list") }
    }
}