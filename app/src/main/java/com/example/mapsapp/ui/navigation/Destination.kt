package com.example.mapsapp.ui.navigation

import kotlinx.serialization.Serializable


sealed class Destination {
    @Serializable object PermissionScreen : Destination()
    @Serializable object LogInScreen : Destination()
    @Serializable object RegisterScreen : Destination()
    @Serializable object MapScreen : Destination()
    @Serializable object MarkerListScreen : Destination()
    @Serializable data class DetailMarkerScreen(val markerId: String) : Destination()
    @Serializable data class CreateMarkerScreen(val latLng: String) : Destination()
    @Serializable object DrawerMenuScreen : Destination()
}