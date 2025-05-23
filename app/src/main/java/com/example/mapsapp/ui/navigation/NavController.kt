package com.example.mapsapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.mapsapp.ui.screens.auth.LogInScreen
import com.example.mapsapp.ui.screens.auth.RegisterScreen
import com.example.mapsapp.ui.screens.main.MapScreen
import com.example.mapsapp.ui.screens.main.MyDrawerMenu
import com.example.mapsapp.ui.screens.main.markers.MarkerListScreen
import com.example.mapsapp.ui.screens.main.markers.DetailMarkerScreen
import com.example.mapsapp.ui.screens.main.markers.CreateMarkerScreen
import com.example.mapsapp.ui.screens.permissions.PermissionScreen
import androidx.compose.ui.Modifier

@Composable
fun NavWrapper(navController: NavHostController, modifier: Modifier) {
    NavHost(navController = navController, startDestination = Destination.MapScreen::class) {



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

@Composable
fun BeforeAuthNavWrapper(navController: NavHostController){
    NavHost(navController = navController, startDestination = Destination.PermissionScreen){
        composable<Destination.PermissionScreen> {
            PermissionScreen(onPermissionGranted = {
                navController.navigate(Destination.LogInScreen)
            })
        }

        composable<Destination.LogInScreen> {
            LogInScreen(
                onLoginSuccess = {
                    navController.navigate(Destination.DrawerMenuScreen)
                },
                onRegisterClick = {
                    navController.navigate(Destination.RegisterScreen)
                }
            )
        }

        composable<Destination.RegisterScreen> {
            RegisterScreen(
                onRegisterSuccess = {
                    navController.navigate(Destination.DrawerMenuScreen)
                },
                onLoginClick = {
                    navController.navigate(Destination.LogInScreen)
                }
            )
        }

        composable<Destination.DrawerMenuScreen> {
            MyDrawerMenu() {
                navController.navigate(Destination.LogInScreen) {
                    popUpTo(0) { inclusive = true }
                }
            }
        }

    }
}
