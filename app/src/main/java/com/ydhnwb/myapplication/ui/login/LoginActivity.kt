package com.ydhnwb.myapplication.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ydhnwb.myapplication.R
import com.ydhnwb.myapplication.databinding.ActivityLoginBinding
import com.ydhnwb.myapplication.ui.main.MainActivity
import com.ydhnwb.myapplication.utils.Utils

class LoginActivity : AppCompatActivity() {
    private lateinit var binding : ActivityLoginBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        doLogin()
    }

    private fun doLogin(){
        binding.loginButton.setOnClickListener {
            val email = binding.emailEditText.text.toString().trim()
            val password = binding.passEditText.text.toString().trim()
            if(validate(email, password)){
                startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                finish()
            }
        }
    }

    private fun setEmailErr(e : String?) {
        binding.emailInput.error = e
    }

    private fun setPassErr(e : String?){
        binding.passInput.error = e
    }

    private fun validate(email: String, pass: String) : Boolean {
        setEmailErr(null)
        setPassErr(null)
        if(!Utils.isValidEmail(email)){
            setEmailErr("Email tidak valid")
            return false
        }

        if(pass.length < 6) {
            setPassErr("Password setidaknya tujuh karakter")
            return false
        }
        return true
    }
}