package com.example.mapsapp.viewmodels

import androidx.lifecycle.ViewModel
import com.example.mapsapp.utils.SharedPreferencesHelper
import android.content.Context
import android.util.Log
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewModelScope
import com.example.mapsapp.MyApplication
import kotlinx.coroutines.launch
import androidx.lifecycle.MutableLiveData
import com.example.mapsapp.utils.AuthState

class AuthViewModel(private val sharedPreferences: SharedPreferencesHelper): ViewModel() {
    private val authManager = MyApplication.database
    private val _email = MutableLiveData<String>()
    val email = _email
    private val _password = MutableLiveData<String>()
    val password = _password
    private val _authState = MutableLiveData<AuthState>()
    val authState = _authState
    private val _showError = MutableLiveData<Boolean>(false)
    val showError = _showError
    private val _user = MutableLiveData<String?>()
    val user = _user

    init {
        checkExistingSession()
    }

    private fun refreshToken() {
        viewModelScope.launch {
            try {
                authManager.refreshSession()
                _authState.value = AuthState.Authenticated
            } catch (e: Exception) {
                sharedPreferences.clear()
                _authState.value = AuthState.Unauthenticated
            }
        }
    }

    private fun checkExistingSession() {
        viewModelScope.launch {
            val accessToken = sharedPreferences.getAccessToken()
            val refreshToken = sharedPreferences.getRefreshToken()
            when {
                !accessToken.isNullOrEmpty() -> refreshToken()
                !refreshToken.isNullOrEmpty() -> refreshToken()
                else -> _authState.value = AuthState.Unauthenticated
            }
        }
    }

    fun signUp() {
        viewModelScope.launch {
            if (_email.value.isNullOrEmpty() || _password.value.isNullOrEmpty()) {
                _authState.value = AuthState.Error("Invalid credentials")
                return@launch
            }
            _authState.value = authManager.signUpWithEmail(_email.value!!, _password.value!!)
            if (_authState.value is AuthState.Error) {
                _showError.value = true
                Log.e("AuthViewModel", "Error signing up: ${_authState.value}")
            } else {
                val session = authManager.retrieveCurrentSession()
                sharedPreferences.saveAuthData(
                    session!!.accessToken,
                    session.refreshToken
                )
            }
        }
    }
    fun signIn() {
        viewModelScope.launch {
            if (_email.value.isNullOrEmpty() || _password.value.isNullOrEmpty()) {
                _authState.value = AuthState.Error("Invalid credentials")
                return@launch
            }
            _authState.value = authManager.signInWithEmail(_email.value!!, _password.value!!)
            if (_authState.value is AuthState.Error) {
                _showError.value = true
            } else {
                val session = authManager.retrieveCurrentSession()
                sharedPreferences.saveAuthData(
                    session!!.accessToken,
                    session.refreshToken
                )
            }
        }
    }
    fun logout() {
        viewModelScope.launch {
            sharedPreferences.clear()
            _authState.value = AuthState.Unauthenticated
        }
    }

    fun editEmail(value: String) {
        _email.value = value
    }

    fun editPassword(value: String) {
        _password.value = value
    }
    fun errorMessageShowed(){
        _showError.value = false
    }

}