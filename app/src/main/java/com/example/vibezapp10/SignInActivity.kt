package com.example.vibezapp10

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SignInActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        findViewById<Button>(R.id.btn_login).setOnClickListener {
            // Handle login
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        findViewById<Button>(R.id.btn_sign_up).setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }

        findViewById<Button>(R.id.btn_apple_signin).setOnClickListener {
            // Handle Apple Sign In
        }

        findViewById<Button>(R.id.btn_google_signin).setOnClickListener {
            // Handle Google Sign In
        }
    }
}