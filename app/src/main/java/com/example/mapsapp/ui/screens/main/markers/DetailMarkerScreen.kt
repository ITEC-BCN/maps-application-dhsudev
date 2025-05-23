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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mapsapp.viewmodels.MainViewModel

@Composable
fun DetailMarkerScreen(
    markerId: String,
    navigateBack: () -> Unit
){
    val myViewModel = viewModel<MainViewModel>()
    myViewModel.getMarker(markerId)
    val marker = myViewModel.marker.value

    Column (
        modifier = Modifier.fillMaxSize().padding(top = 100.dp, start = 16.dp, end = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text("This is the Detail Marker Screen")
        Text("Title: ${marker?.title}")
        Text("Description: ${marker?.desc}")
        Text("Position: ${marker?.long}, ${marker?.lat}")
        Button(onClick = { navigateBack() }) { Text("Go back to list") }
    }
}