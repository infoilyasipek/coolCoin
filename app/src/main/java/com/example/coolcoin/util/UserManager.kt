package com.example.coolcoin.util

import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserManager @Inject constructor() {

    val isSignedIn: Boolean
        get() = userId != null

    val userId: String?
        get() = FirebaseAuth.getInstance().currentUser?.uid

}
