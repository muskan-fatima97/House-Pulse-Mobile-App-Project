package com.alpha.housepulse

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.alpha.housepulse.databinding.ActivitySignUpBinding

class SignUp : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSignup.setOnClickListener {
            if (validateInput()) {
                val intent = Intent(this, BottomNavigation::class.java)
                startActivity(intent)
            }
        }

        binding.tvLogIn.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }
    }

    private fun validateInput(): Boolean {
        val email = binding.etEmail.text.toString().trim()
        val phoneNumber = binding.etPhoneNumber.text.toString().trim()
        val password = binding.etSetPassword.text.toString().trim()
        val resetPassword = binding.etResetPassword.text.toString().trim()

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.etEmail.error = "Invalid email format"
            binding.etEmail.requestFocus()
            return false
        }

        if (!phoneNumber.matches(Regex("^[0-9]{11}\$"))) {
            binding.etPhoneNumber.error = "Phone number must be exactly 11 digits"
            binding.etPhoneNumber.requestFocus()
            return false
        }

        if (password.length < 8) {
            binding.etSetPassword.error = "Password must be at least 8 characters"
            binding.etSetPassword.requestFocus()
            return false
        }

        if (password != resetPassword) {
            binding.etResetPassword.error = "Passwords do not match"
            binding.etResetPassword.requestFocus()
            return false
        }

        return true
    }
}
