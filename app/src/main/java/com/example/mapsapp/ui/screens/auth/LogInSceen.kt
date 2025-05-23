package com.example.mapsapp.ui.screens.auth

import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.material3.Text
import com.example.mapsapp.utils.AuthState
import com.example.mapsapp.utils.SharedPreferencesHelper
import com.example.mapsapp.viewmodels.AuthViewModelFactory
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.platform.LocalContext
import android.widget.Toast
import androidx.compose.material3.TextField
import androidx.compose.runtime.getValue
import com.example.mapsapp.viewmodels.AuthViewModel
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.TextButton
import androidx.compose.ui.graphics.Color

@Composable
fun LogInScreen(onRegisterClick: () -> Unit, onLoginSuccess: () -> Unit) {
    val context = LocalContext.current
    val vm: AuthViewModel = viewModel(factory = AuthViewModelFactory(SharedPreferencesHelper(context)))
    val authState by vm.authState.observeAsState()
    val showError by vm.showError.observeAsState(false)
    val email by vm.email.observeAsState("")
    val password by vm.password.observeAsState("")

    Column (modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally){
        Text("This is the Login Screen")
        if(authState == AuthState.Authenticated){
            onLoginSuccess()
        }
        else{
            if (showError) {
                val errorMessage = (authState as AuthState.Error).message
                if (errorMessage!!.contains("invalid_credentials")) {
                    Toast.makeText(context, "Invalid credentials", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(context, "An error has ocurred", Toast.LENGTH_LONG).show()
                }
                vm.errorMessageShowed()
            }

        }
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(value = email, onValueChange = {vm.editEmail(it)})
            TextField(value = password, onValueChange = {vm.editPassword(it)})
            Button(onClick = {vm.signIn()}) {
                Text("Log in")
            }
            Row (verticalAlignment = Alignment.CenterVertically) {
                Text("Don't have an account?")
                TextButton(
                    onClick = onRegisterClick,
                ) {
                    Text("Sign up!")
                }
            }
        }
    }
}