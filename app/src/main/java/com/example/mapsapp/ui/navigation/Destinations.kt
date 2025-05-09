package com.example.mapsapp.ui.navigation

import com.example.mapsapp.utils.LatLngSerializer
import com.google.android.gms.maps.model.LatLng
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Serializable
sealed class Destinations() {
    @Serializable object PermissionScreen
    @Serializable data class LogInScreen(
        val onRegisterClick: () -> Unit,
        val onLoginSuccess: () -> Unit
    )
    @Serializable data class RegisterScreen(
        val onLogInClick: () -> Unit,
        val onRegisterSuccess: () -> Unit
    )
    @Serializable data class MapScreen(
        val onLongClick: (@Serializable(with = LatLngSerializer::class)  LatLng) -> Unit
    )
    @Serializable object MarkerListScreen
    @Serializable data class DetailMarkerScreen(
        val markerId: String,
        val navigateBack: () -> Boolean
    )
    @Serializable data class CreateMarkerScreen(
        // LatLng class does not have a serializer,
        // so we need to use our custom one
        @Serializable(with = LatLngSerializer::class) val latLng: LatLng
    )

}