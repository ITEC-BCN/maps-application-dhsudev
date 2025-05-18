package com.example.mapsapp.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.ui.graphics.vector.ImageVector

enum class DrawerItem(
    val icon: ImageVector,
    val text: String,
    val route: Destination? // todo: entender porq no van las rutas :(
) {
    MAP(Icons.Default.LocationOn, "Map", null),
    LIST(Icons.Default.Edit, "List", null)
}
