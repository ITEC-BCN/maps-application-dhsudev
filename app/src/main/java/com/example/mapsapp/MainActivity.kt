package com.example.mapsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.mapsapp.ui.navigation.BeforeAuthNavWrapper
import com.example.mapsapp.ui.navigation.NavWrapper
import com.example.mapsapp.ui.screens.main.MyDrawerMenu
import com.example.mapsapp.ui.screens.permissions.PermissionScreen
import com.example.mapsapp.ui.theme.MapsAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MapsAppTheme {
                val navController = rememberNavController()
                BeforeAuthNavWrapper(navController)
            }
        }
    }
}
