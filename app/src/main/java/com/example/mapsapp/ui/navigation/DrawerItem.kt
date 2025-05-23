package com.example.mapsapp.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

enum class DrawerItem(
    val icon: ImageVector,
    val text: String,
    val route: Destination
) {
    MAP(Icons.Default.LocationOn, "Map", Destination.MapScreen),
    LIST(Icons.Default.Edit, "List", Destination.MarkerListScreen),
    LOG_OUT(Icons.Default.ExitToApp, "Log out", Destination.LogInScreen)
}
