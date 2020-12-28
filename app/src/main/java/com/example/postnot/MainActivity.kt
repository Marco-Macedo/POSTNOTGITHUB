package com.example.postnot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class MainActivity : AppCompatActivity() {
    // Variáveis globais //
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        auth = FirebaseAuth.getInstance()
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }

    fun updateUI(currentUser: FirebaseUser?)
    {

    }
    fun login(view: View) {
        Toast.makeText(this, "Login ainda não codificado", Toast.LENGTH_SHORT).show()

    }

    fun registar(view: View) {
        startActivity(Intent(this, SignUpActivity::class.java))
    }


}