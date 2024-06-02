package com.example.hamrogrocery

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.hamrogrocery.databinding.ActivityForgetBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidUserException

class ForgetActivity : AppCompatActivity() {
    lateinit var forgetBinding: ActivityForgetBinding
    var auth= FirebaseAuth.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        forgetBinding=ActivityForgetBinding.inflate(layoutInflater)
        setContentView(forgetBinding.root)

        forgetBinding.forgetbtn.setOnClickListener {
            val email = forgetBinding.emailtexthae.text.toString().trim()
            if (email.isEmpty()) {
                forgetBinding.emailtexthae.error = "Email is required"
                return@setOnClickListener
            }
            auth.sendPasswordResetEmail(email).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Email sent to reset password", Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    val exception = task.exception
                    if (exception is FirebaseAuthInvalidUserException) {
                        forgetBinding.emailtexthae.error = "Email is not registered"
                    } else {
                        forgetBinding.emailtexthae.error = "Failed to send reset email: ${exception?.localizedMessage}"
                    }
                }
            }
        }
    }
}