package com.example.hamrogrocery

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.hamrogrocery.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth

class SignUpActivity : AppCompatActivity() {
    private lateinit var signupBinding: ActivitySignUpBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        signupBinding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(signupBinding.root)

        auth = FirebaseAuth.getInstance()


        signupBinding.signupBtn.setOnClickListener {
            if (!signupBinding.agreeCheckBox.isChecked) {
                Toast.makeText(
                    this,
                    "You must agree to the terms and conditions",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }
            val username = signupBinding.username.text.toString().trim()
            val password = signupBinding.password.text.toString().trim()
            val phone = signupBinding.phoneNo.text.toString().trim()

            if (validateInput(username, password, phone)) {
                createUser(username, password)
            }
        }
    }

    private fun validateInput(username: String, password: String, phone: String): Boolean {
        if (username.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(username).matches()) {
            Toast.makeText(this, "Please enter a valid email", Toast.LENGTH_SHORT).show()
            return false
        }

        if (password.isEmpty() || password.length < 6) {
            Toast.makeText(this, "Password must be at least 6 characters", Toast.LENGTH_SHORT).show()
            return false
        }

        if (phone.isEmpty() || !android.util.Patterns.PHONE.matcher(phone).matches()) {
            Toast.makeText(this, "Please enter a valid phone number", Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }

    private fun createUser(username: String, password: String) {
        signupBinding.signupBtn.isEnabled = false
        auth.createUserWithEmailAndPassword(username, password).addOnCompleteListener { task ->
            signupBinding.signupBtn.isEnabled = true
            if (task.isSuccessful) {
                Toast.makeText(this@SignUpActivity, "User Created", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(
                    this@SignUpActivity,
                    task.exception?.message.toString(),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}
