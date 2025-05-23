package com.example.mapsapp.data

import com.example.mapsapp.utils.AuthState
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.builtin.Email
import io.github.jan.supabase.auth.user.UserSession
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.from

class MySupabaseClient() {
    lateinit var supabase: SupabaseClient
    constructor(supabaseUrl: String, supabaseKey: String): this(){
        supabase = createSupabaseClient(
            supabaseUrl = supabaseUrl,
            supabaseKey = supabaseKey
        ) {
            install(Postgrest)
            install(Auth){autoLoadFromStorage = true}
        }
    }
    //SQL operations
    suspend fun getAllMarkers(): List<Marker> {
        return supabase.from("Marker").select().decodeList<Marker>()
    }

    suspend fun getMarker(id: String): Marker{
        return supabase.from("Marker").select {
            filter {
                eq("id", id)
            }
        }.decodeSingle<Marker>()
    }
    suspend fun insertMarker(marker: Marker){
        supabase.from("Marker").insert(marker)
    }
    suspend fun updateMarker(id: String, name: String, mark: Double){
        supabase.from("Marker").update({
            set("name", name)
            set("mark", mark)
        }) { filter { eq("id", id) } }
    }
    suspend fun deleteMarker(id: String){
        supabase.from("Marker").delete{ filter { eq("id", id) } }
    }

    // AUTH FUNCTIONS
    suspend fun signUpWithEmail(emailValue: String, passwordValue: String): AuthState {
        try {
            supabase.auth.signUpWith(Email) {
                email = emailValue
                password = passwordValue
            }
            return AuthState.Authenticated
        } catch (e: Exception) {
            return AuthState.Error(e.localizedMessage)
        }
    }
    suspend fun signInWithEmail(emailValue: String, passwordValue: String): AuthState {
        try {
            supabase.auth.signInWith(Email) {
                email = emailValue
                password = passwordValue
            }
            return AuthState.Authenticated
        } catch (e: Exception) {
            return AuthState.Error(e.localizedMessage)
        }
    }
    fun retrieveCurrentSession(): UserSession?{
        val session = supabase.auth.currentSessionOrNull()
        return session
    }
    fun refreshSession(): AuthState {
        try {
            supabase.auth.currentSessionOrNull()
            return AuthState.Authenticated
        } catch (e: Exception) {
            return AuthState.Error(e.localizedMessage)
        }
    }


}
