package com.example.mapsapp.ui.screens.main.markers

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mapsapp.data.Marker
import com.example.mapsapp.viewmodels.MainViewModel

@Composable
fun MarkerListScreen(navigateToDetail : (String) -> Unit) {
    val myViewModel = viewModel<MainViewModel>()
    val markerTitle: String by myViewModel.markerTitle.observeAsState("")

    val markersList by myViewModel.markersList.observeAsState(emptyList<Marker>())

    myViewModel.getAllMarkers()
    LazyColumn(
        Modifier.fillMaxWidth().padding(top = 100.dp, start = 16.dp, end = 16.dp)
    ) {
        items(markersList) { marker ->
            val dissmissState = rememberSwipeToDismissBoxState(
                confirmValueChange = {
                    if (it == SwipeToDismissBoxValue.EndToStart) {
                        myViewModel.deleteMarker(marker.id)
                        true
                    } else false
                }
            )
            SwipeToDismissBox(
                state = dissmissState,
                backgroundContent = {
                    Box(
                        Modifier
                            .fillMaxSize()
                            .background(Color.Red),
                        contentAlignment = Alignment.CenterEnd
                    ) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Delete",
                            tint = Color.White,
                            modifier = Modifier.padding(end = 24.dp)
                        )
                    }
                }
            ) {
                MarkerItem(marker) { navigateToDetail(marker.id.toString()) }
            }
            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}
@Composable
fun MarkerItem(marker: Marker, navigateToDetail: (String) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.LightGray)
            .border(width = 2.dp, color = Color.DarkGray)
            .clickable { navigateToDetail(marker.id.toString()) }
            .padding(12.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = marker.title,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = marker.desc,
                fontSize = 16.sp
            )
        }
    }
}