package com.example.mapsapp

import android.app.Application
import com.example.mapsapp.data.MySupabaseClient

class MyApplication: Application() {
    companion object {
        lateinit var database: MySupabaseClient
    }
    override fun onCreate() {
        super.onCreate()
        database = MySupabaseClient(
            supabaseUrl = "https://aefcfcwopgxfwvipnmvf.supabase.co",
            supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImFlZmNmY3dvcGd4Znd2aXBubXZmIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDY3ODI5ODgsImV4cCI6MjA2MjM1ODk4OH0.nV_6vP-N301nnAZ6AtSPJhTSqTfKjAFcmgGegOF4yyc"
        )

    }
}

