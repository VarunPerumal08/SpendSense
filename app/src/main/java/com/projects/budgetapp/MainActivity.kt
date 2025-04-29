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
            // TODO: Implement Google Sign In
        }

        // Handle Facebook Sign In button click
        facebookSignInButton.setOnClickListener {
            // TODO: Implement Facebook Sign In
        }

        // Handle Email Sign In button click
        emailSignInButton.setOnClickListener {
            // TODO: Navigate to email sign-in screen

        }

        // Handle existing user login
        loginButton.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}