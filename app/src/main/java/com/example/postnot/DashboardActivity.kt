package com.example.postnot

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem

class DashboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {

            R.id.logout -> {
                var token = getSharedPreferences("login", Context.MODE_PRIVATE)
                var editor = token.edit()
                editor.putInt("loginuser",0)        // Iguala valor a 0, fica sem valor, credenciais soltas
                editor.commit()                                     // Atualizar editor
                val intent = Intent(this@DashboardActivity, MainActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }
}