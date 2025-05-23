package com.example.mapsapp.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mapsapp.MyApplication
import com.example.mapsapp.data.Marker
import com.example.mapsapp.utils.PermissionStatus
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.collections.set

class MainViewModel : ViewModel() {
    private val database = MyApplication.database
    private val _markerTitle = MutableLiveData<String>()
    val markerTitle = _markerTitle
    private val _markerMark = MutableLiveData<String>()
    val markerMark = _markerMark
    fun insertNewMarker(
        markerId: String,
        owner: String = "none",
        long: Double,
        lat: Double,
        title: String,
        desc: String,
        image: String
    ) {
        val newMarker = Marker(markerId, owner, long, lat, title, desc, image)
        CoroutineScope(Dispatchers.IO).launch {
            database.insertMarker(newMarker)
            getAllMarkers()
        }
    }

    private val _markersList = MutableLiveData<List<Marker>>()
    val markersList = _markersList

    fun getAllMarkers() {
        CoroutineScope(Dispatchers.IO).launch {
            val databaseMarkers = database.getAllMarkers()
            withContext(Dispatchers.Main) {
                _markersList.value = databaseMarkers
            }
        }
    }

    fun deleteMarker(id: String){
        CoroutineScope(Dispatchers.IO).launch {
            database.deleteMarker(id)
            getAllMarkers()
        }
    }


}