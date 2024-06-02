package com.example.hamrogrocery.fragments

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.hamrogrocery.DashActivity
import com.example.hamrogrocery.ForgetActivity
import com.example.hamrogrocery.R
import com.example.hamrogrocery.SignUpActivity
import com.example.hamrogrocery.databinding.FragmentProfileBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

class ProfileFragment : Fragment() {
    lateinit var profileBinding: FragmentProfileBinding
    lateinit var sharedPreferences: SharedPreferences
    private lateinit var auth: FirebaseAuth

    private val RC_SIGN_IN = 9001

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       profileBinding= FragmentProfileBinding.inflate(layoutInflater)
        auth = FirebaseAuth.getInstance()

        profileBinding.register.setOnClickListener {
            var intent=Intent(activity,SignUpActivity::class.java)
            startActivity(intent)
        }

        profileBinding.forgotPassword.setOnClickListener {
            var intent=Intent(activity, ForgetActivity::class.java)
            startActivity(intent)
        }

        profileBinding.signinBtn.setOnClickListener {
            val email= profileBinding.userField.text.toString().trim()
            val password= profileBinding.passfield.text.toString().trim()

            if(email.isNotEmpty() && password.isNotEmpty()){
                signInWithEmailPassword(email,password)
                }
            else{
                Toast.makeText(activity, "Please enter email and password", Toast.LENGTH_SHORT).show()
            }
        }

        profileBinding.forgotPassword.setOnClickListener {
            val intent = Intent(activity, ForgetActivity::class.java)
            startActivity(intent)
        }

        profileBinding.imageView6.setOnClickListener {
            signInWithGoogle()
        }


        return profileBinding.root
    }

    private fun signInWithEmailPassword(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val intent = Intent(activity, DashActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(activity, "Authentication failed.", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun signInWithGoogle() {
        auth.signOut()
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("964351838101-pngbfdfre9mih9f1nai3v5etu7mj7s9s.apps.googleusercontent.com")
            .requestEmail()
            .build()

        val googleSignInClient = GoogleSignIn.getClient(requireActivity(), gso)
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
                Toast.makeText(activity, "Google sign in failed: ${e.statusCode}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(activity, "Google sign in successful", Toast.LENGTH_SHORT).show()
                    val intent = Intent(activity, DashActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(activity, "Google sign in failed", Toast.LENGTH_SHORT).show()
                }
            }
    }


}