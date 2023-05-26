package com.rajkishorbgp.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.rajkishorbgp.quizapp.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding= ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //val auth = Firebase.auth

        binding.button.setOnClickListener{
            Firebase.auth.createUserWithEmailAndPassword(binding.email.editText?.text.toString(),
            binding.password.editText?.text.toString()).addOnCompleteListener{
                if (it.isSuccessful){
                    Toast.makeText(this,"User created!!!",Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this,QuizActivity::class.java))
                    finish()

                }else{
                    Toast.makeText(this,it.exception.toString(),Toast.LENGTH_LONG).show()
                }
            }
        }

    }
}