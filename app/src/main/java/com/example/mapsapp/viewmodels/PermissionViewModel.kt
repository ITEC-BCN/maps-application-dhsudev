package com.example.mapsapp.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.mapsapp.utils.PermissionStatus
import androidx.compose.runtime.State

class PermissionViewModel: ViewModel() {
    private val _permissionsStatus = mutableStateOf<Map<String, PermissionStatus>>(emptyMap())
    val permissionsStatus: State<Map<String, PermissionStatus>> = _permissionsStatus

    private val _allPermissionsGranted = mutableStateOf(false)
    val allPermissionsGranted: State<Boolean> = _allPermissionsGranted

    fun updatePermissionStatus(permission: String, status: PermissionStatus) {
        _permissionsStatus.value = _permissionsStatus.value.toMutableMap().apply {
            this[permission] = status
        }
        checkAllPermissionsGranted()
    }

    fun checkAllPermissionsGranted() {
        _allPermissionsGranted.value = _permissionsStatus.value.values.all { it == PermissionStatus.Granted }
    }

}
