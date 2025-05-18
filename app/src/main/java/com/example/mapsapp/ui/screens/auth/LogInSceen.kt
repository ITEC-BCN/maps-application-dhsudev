package com.example.mapsapp.ui.screens.auth

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.material3.Text

@Composable
fun LogInScreen(onRegisterClick: () -> Unit, onLoginSuccess: () -> Unit) {
    Column (modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally){
        Text("This is the Login Screen")
        Button(onClick = onRegisterClick ) {Text("Navigate to Register")}
        Button(onClick = onLoginSuccess ){Text("Navigate to Map")}
    }
}