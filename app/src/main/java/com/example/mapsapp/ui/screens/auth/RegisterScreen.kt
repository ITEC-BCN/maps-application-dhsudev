package com.example.mapsapp.ui.screens.auth

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun RegisterScreen(onLoginClick: () -> Unit, onRegisterSuccess: () -> Unit) {
    Column (modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally){
        Text("This is the Register Screen")
        Button(onClick = onLoginClick) {Text("Navigate to LogIn")}
        Button(onClick = onRegisterSuccess) {Text("Navigate to Map")}
    }
}