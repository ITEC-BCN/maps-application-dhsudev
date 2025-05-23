package com.example.mapsapp.ui.screens.auth

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mapsapp.utils.AuthState
import com.example.mapsapp.utils.SharedPreferencesHelper
import com.example.mapsapp.viewmodels.AuthViewModel
import com.example.mapsapp.viewmodels.AuthViewModelFactory
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun RegisterScreen(
    onLoginClick: () -> Unit,
    onRegisterSuccess: () -> Unit
) {
    val context = LocalContext.current
    val viewModel: AuthViewModel =
        viewModel(factory = AuthViewModelFactory(SharedPreferencesHelper(context)))
    val authState by viewModel.authState.observeAsState()
    val showError by viewModel.showError.observeAsState(false)
    val email by viewModel.email.observeAsState("")
    val password by viewModel.password.observeAsState("")

    //Si ya tiene cuenta no hace falta iniciar sesión de nuevo
    if (authState == AuthState.Authenticated) {
        onRegisterSuccess()
    } else {
        //En caso de error
        if (showError) {
            val errorMessage = (authState as AuthState.Error).message
            if (errorMessage.contains("invalid_credentials")) {
                Toast.makeText(context, "Invalid credentials", Toast.LENGTH_LONG).show()
            } else if (errorMessage.contains("weak_password")) {
                Toast.makeText(
                    context,
                    "Password should be at least 6 characters",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                Toast.makeText(context, "An error has ocurred", Toast.LENGTH_LONG).show()
            }
            viewModel.errorMessageShowed()
        }

    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(value = email, onValueChange = {viewModel.editEmail(it)})
        TextField(value = password, onValueChange = {viewModel.editPassword(it)})
        Button(onClick = {
            viewModel.signUp()
            if(authState == AuthState.Authenticated){
                onRegisterSuccess()
            }
        }) {
            Text("Register")
        }
        Row (verticalAlignment = Alignment.CenterVertically) {
            Text("Already have an account?")
            TextButton(onClick = onLoginClick) {
                Text("Register!")
            }
        }
    }
}
