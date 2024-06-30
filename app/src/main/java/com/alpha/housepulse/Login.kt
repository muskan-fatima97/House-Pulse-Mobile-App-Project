package com.alpha.housepulse

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import androidx.appcompat.app.AppCompatActivity
import com.alpha.housepulse.databinding.ActivityLoginBinding

class Login : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            if (validateInput()) {
                val intent = Intent(this, BottomNavigation::class.java)
                startActivity(intent)
            }
        }

        binding.tvSignUp.setOnClickListener {
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
        }
    }

    private fun validateInput(): Boolean {
        val email = binding.etEmail.text.toString().trim()
        val phoneNumber = binding.etNumber.text.toString().trim()
        val password = binding.etPassword.text.toString().trim()

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.etEmail.error = "Invalid email format"
            binding.etEmail.requestFocus()
            return false
        }

        if (!phoneNumber.matches(Regex("^[0-9]{11}\$"))) {
            binding.etNumber.error = "Phone number must be exactly 11 digits"
            binding.etNumber.requestFocus()
            return false
        }

        if (password.length < 8) {
            binding.etPassword.error = "Password must be at least 8 characters"
            binding.etPassword.requestFocus()
            return false
        }

        return true
    }
}
