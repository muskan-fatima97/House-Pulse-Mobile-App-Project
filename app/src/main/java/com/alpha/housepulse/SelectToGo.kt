package com.alpha.housepulse

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.alpha.housepulse.databinding.ActivitySelectToGoBinding

class SelectToGo : AppCompatActivity() {


    private lateinit var binding: ActivitySelectToGoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding= ActivitySelectToGoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnGoLogin.setOnClickListener {
            val intent= Intent(this,Login::class.java)
            startActivity(intent)
        }
        binding.btnSignup.setOnClickListener {
            val a= Intent(this, SignUp::class.java)
            startActivity(a)
        }
    }
}