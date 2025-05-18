package com.example.mapsapp.ui.navigation

import kotlinx.serialization.Serializable


sealed class Destination {
    @Serializable object PermissionScreen
    @Serializable object LogInScreen
    @Serializable object RegisterScreen
    @Serializable object MapScreen
    @Serializable object MarkerListScreen
    @Serializable data class DetailMarkerScreen(val markerId: String)
    @Serializable data class CreateMarkerScreen(
        val latLng: String
    )
}