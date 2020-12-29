package com.example.postnot

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService() {

    /*override fun onMessageReceived(p0: RemoteMessage) {
        super.onMessageReceived(p0)

        Log.e("message", "Message Received ...");
    }*/

    override fun onNewToken(token: String) {
        Log.e("TAG", "the token refreshed: $token")
    }

}