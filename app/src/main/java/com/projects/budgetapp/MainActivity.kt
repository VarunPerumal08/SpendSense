package com.projects.budgetapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val googleSignInButton = findViewById<Button>(R.id.btnGoogleSignIn)
        val facebookSignInButton = findViewById<Button>(R.id.btnFacebookSignIn)
        val emailSignInButton = findViewById<Button>(R.id.btnEmailSignIn)
        val loginButton = findViewById<Button>(R.id.btnLogin)

        // Handle Google Sign In button click
        googleSignInButton.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        // Handle Facebook Sign In button click
        facebookSignInButton.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        // Handle Email Sign In button click
        emailSignInButton.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)

        }

        // Handle existing user login
        loginButton.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}
// Implementation based on: Bazarbay (2022)