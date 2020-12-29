package com.example.postnot

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_sign_up.*
import java.lang.Boolean.TRUE
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {
    // Variáveis globais //
    private lateinit var auth: FirebaseAuth
    private var loginuser : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        auth = FirebaseAuth.getInstance()
    }

    fun login(view: View) {
        doLogin()
    }

    private fun doLogin() {
        // VALIDAR EMAIL E PASSWORD
        if(email.text.toString().isEmpty()) {
            email.error = "Please enter email"
            email.requestFocus()
            return
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email.text.toString()).matches()){
            email.error = "Please enter valid email"
            email.requestFocus()
            return
        }

        //*********************************************************************************
        // PASSWORD
        if(password.text.toString().isEmpty()){
            password.error= "Please enter password"
            password.requestFocus()
            return
        }
        auth.signInWithEmailAndPassword(email.text.toString(), password.text.toString())
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        val user = auth.currentUser
                        updateUI(user)
                        loginuser = 1
                    } else {
                        loginuser = 0
                        Toast.makeText(baseContext, "Authentication failed.",
                                Toast.LENGTH_SHORT).show()
                        updateUI(null)
                    }
                }
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser      // AUTENTICAÇÃO AUTOMATICA
        if(currentUser != null)
        {
            loginuser = 1
        }else
        {
            loginuser = 0
        }
        updateUI(currentUser)           // diz qual o user logado, caso exista...
    }

    fun registar(view: View) {
        startActivity(Intent(this, SignUpActivity::class.java))
    }

    private fun updateUI(currentUser: FirebaseUser?) {
        var token = getSharedPreferences("login", Context.MODE_PRIVATE)
        var editor = token.edit()
        editor.putInt("loginuser",loginuser)
        editor.commit()

        if(currentUser != null && loginuser == 1){
            if(currentUser.isEmailVerified) {           // EMAIL É VERIFICADO?? Entra na
                startActivity(Intent(this, DashboardActivity::class.java))
                finish()    // close app
            }else{
                Toast.makeText(
                    baseContext, "Please verify your email address.",
                    Toast.LENGTH_SHORT
                ).show()
            }
            }
    }

}