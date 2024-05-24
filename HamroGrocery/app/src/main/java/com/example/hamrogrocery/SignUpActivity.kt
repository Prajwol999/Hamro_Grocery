package com.example.hamrogrocery

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.hamrogrocery.databinding.ActivitySignUpBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

class SignUpActivity : AppCompatActivity() {
    private lateinit var signupBinding: ActivitySignUpBinding
    private lateinit var auth: FirebaseAuth

    private val RC_SIGN_IN = 9001

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

        signupBinding.imageView3.setOnClickListener {
            signInWithGoogle()
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

    private fun signInWithGoogle() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("964351838101-pngbfdfre9mih9f1nai3v5etu7mj7s9s.apps.googleusercontent.com")
            .requestEmail()
            .build()


        val googleSignInClient = GoogleSignIn.getClient(this, gso)
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)!!
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                Toast.makeText(this, "Google sign in failed: ${e.statusCode}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Toast.makeText(this, "Google sign in successful", Toast.LENGTH_SHORT).show()
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(this, "Google sign in failed", Toast.LENGTH_SHORT).show()
                }
            }
    }
}
