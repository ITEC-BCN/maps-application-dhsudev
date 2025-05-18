package com.example.mapsapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.mapsapp.ui.screens.auth.LogInScreen
import com.example.mapsapp.ui.screens.auth.RegisterScreen
import com.example.mapsapp.ui.screens.main.MapScreen
import com.example.mapsapp.ui.screens.main.markers.MarkerListScreen
import com.example.mapsapp.ui.screens.main.markers.DetailMarkerScreen
import com.example.mapsapp.ui.screens.main.markers.CreateMarkerScreen
import com.example.mapsapp.ui.screens.permissions.PermissionScreen

@Composable
fun NavWrapper(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Destination.PermissionScreen::class) {

        composable<Destination.PermissionScreen> {
            PermissionScreen(onPermissionGranted = {
                navController.navigate(Destination.LogInScreen)
            })
        }

        composable<Destination.LogInScreen> {
            LogInScreen(
                onLoginSuccess = {
                    navController.navigate(Destination.MapScreen)
                },
                onRegisterClick = {
                    navController.navigate(Destination.RegisterScreen)
                }
            )
        }

        composable<Destination.RegisterScreen> {
            RegisterScreen(
                onRegisterSuccess = {
                    navController.navigate(Destination.MapScreen)
                },
                onLoginClick = {
                    navController.navigate(Destination.LogInScreen)
                }
            )
        }


        composable<Destination.MapScreen> {
            MapScreen(
                onLongClick = { latLng ->
                    navController.navigate(Destination.CreateMarkerScreen(latLng))
                }
            )
        }

        composable<Destination.MarkerListScreen> {
            MarkerListScreen(
                navigateToDetail = { markerId ->
                    navController.navigate(Destination.DetailMarkerScreen(markerId))
                }
            )
        }


        composable<Destination.DetailMarkerScreen> {
            val args = it.toRoute<Destination.DetailMarkerScreen>()
            DetailMarkerScreen(
                markerId = args.markerId,
                navigateBack = { navController.popBackStack() }
            )
        }

        composable<Destination.CreateMarkerScreen> {
            val args = it.toRoute<Destination.CreateMarkerScreen>()
            CreateMarkerScreen(latLng = args.latLng)
        }
    }
}
