package com.example.coolcoin

import android.app.Application
import com.google.firebase.FirebaseOptions
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.initialize
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class CoolCoinApplication: Application(){
    override fun onCreate() {
        super.onCreate()

        val options = FirebaseOptions.Builder()
            .setProjectId("coolcoin-f7907")
            .setApplicationId("1:421160514079:android:4059ae33671895208a9b2a")
            .setApiKey("AIzaSyDc6nnzWwshG8mtyVSMjIgXUUdnGM_Gmxk")
            .setStorageBucket("coolcoin-f7907.appspot.com")
            .build()

        Firebase.initialize(this, options)
    }
}
