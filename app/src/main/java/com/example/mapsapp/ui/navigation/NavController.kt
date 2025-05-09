package com.example.mapsapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.mapsapp.ui.navigation.Destinations.*
import com.example.mapsapp.ui.screens.auth.LogInScreen
import com.example.mapsapp.ui.screens.auth.RegisterScreen
import com.example.mapsapp.ui.screens.main.MapScreen
import com.example.mapsapp.ui.screens.main.markers.MarkerListScreen
import com.example.mapsapp.ui.screens.permissions.PermissionScreen
import com.example.mapsapp.utils.LatLngSerializer
import com.google.android.gms.maps.model.LatLng

@Composable
fun NavWrapper(navController: NavHostController) {
    NavHost(navController = navController, startDestination = PermissionScreen::class) {
        // REQUEST PERMISSIONS
        composable<Destinations.PermissionScreen> {
            PermissionScreen(onPermissionGranted = {
                navController.navigate(LogInScreen)
            })
        }

        // AUTH
        composable<LogInScreen> {
            LogInScreen(onLoginSuccess = {
                navController.navigate(MapScreen)
            }, onRegisterClick = {
                navController.navigate(RegisterScreen)
            })
        }

        composable<RegisterScreen> {
            RegisterScreen(onRegisterSuccess = {
                navController.navigate(MapScreen)
            }, onLoginClick = {
                navController.navigate(LogInScreen)
            })
        }

        // MAIN APP
        composable<MapScreen> {
            MapScreen(onLongClick = { latLng ->
                navController.navigate(CreateMarkerScreen(latLng)) // Create Marker when longClick
            })
        }
        composable<MarkerListScreen> {
            MarkerListScreen(navigateToDetail = { markerId ->
                navController.navigate(DetailMarkerScreen(markerId) {
                    navController.popBackStack()
                })
            })
        }
        composable<DetailMarkerScreen> { backStackEntry ->
            val screenArgs = backStackEntry.toRoute<DetailMarkerScreen>()
            DetailMarkerScreen(
                markerId = screenArgs.markerId,
                navigateBack = { navController.popBackStack() })
        }
        composable<CreateMarkerScreen> { backStackEntry ->
            val screenArgs = backStackEntry.toRoute<CreateMarkerScreen>()
            CreateMarkerScreen(latLng = screenArgs.latLng)
        }
    }
}
