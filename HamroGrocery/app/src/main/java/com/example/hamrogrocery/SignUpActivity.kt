package com.example.hamrogrocery

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.hamrogrocery.databinding.ActivitySignUpBinding
import com.example.hamrogrocery.fragments.ProfileFragment
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.database.FirebaseDatabase

class SignUpActivity : AppCompatActivity() {
    lateinit var signupBinding: ActivitySignUpBinding
    lateinit var sharedPreferences: SharedPreferences
    private lateinit var auth: FirebaseAuth
    private val RC_SIGN_IN = 9001


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        signupBinding= ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(signupBinding.root)
        auth= FirebaseAuth.getInstance()

        signupBinding.signupBtn.setOnClickListener {
            if(!signupBinding.agreeCheckBox.isChecked) {
                Toast.makeText(this, "Please agree to the terms and conditions", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }
            val email = signupBinding.username.text.toString()
            val password = signupBinding.password.text.toString()
            val phoneNum = signupBinding.phoneNo.text.toString()

            if(validateInput(email, password, phoneNum)){
                createUserWithEmailAndPassword(email, password, phoneNum)
            }
        }

        signupBinding.imageView3.setOnClickListener {
            signInWithGoogle()
        }

        signupBinding.SignIn.setOnClickListener {
            finish()
        }

        signupBinding.terms.setOnClickListener {
            var intent=Intent(this@SignUpActivity,ConditionActivity::class.java)
            startActivity(intent)
        }
    }
    private fun validateInput(email: String, password: String, phoneNum: String):Boolean{
        if(email.isEmpty()|| !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            Toast.makeText(this,"Please enter a valid email", Toast.LENGTH_SHORT).show()
            return false
        }
        if(password.isEmpty()|| password.length<8){
            Toast.makeText(this,"Please enter a valid password", Toast.LENGTH_SHORT).show()
            return false
        }
        if(phoneNum.isEmpty()|| phoneNum.length<10){
            Toast.makeText(this,"Please enter a valid phone number", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    private fun createUserWithEmailAndPassword(email: String, password: String, phoneNum: String) {
        signupBinding.signupBtn.isEnabled = false
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            signupBinding.signupBtn.isEnabled = true
            if (task.isSuccessful) {
                val userId = auth.currentUser?.uid
                val userRef = FirebaseDatabase.getInstance().reference.child("users").child(userId!!)
                userRef.child("phone").setValue(phoneNum)
                Toast.makeText(this@SignUpActivity, "SignUp Successful", Toast.LENGTH_SHORT).show()
                finish()
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
        auth.signOut()
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("964351838101-pngbfdfre9mih9f1nai3v5etu7mj7s9s.apps.googleusercontent.com")
            .requestEmail()
            .build()

        val googleSignInClient = GoogleSignIn.getClient(this, gso)
        googleSignInClient.signOut().addOnCompleteListener {
            val signInIntent = googleSignInClient.signInIntent
            startActivityForResult(signInIntent, RC_SIGN_IN)
        }
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
                    Toast.makeText(this, "Google sign in successful", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Google sign in failed", Toast.LENGTH_SHORT).show()
                }
            }
    }
}



