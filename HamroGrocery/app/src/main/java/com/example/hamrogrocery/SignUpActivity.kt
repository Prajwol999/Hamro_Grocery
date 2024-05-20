package com.example.hamrogrocery

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.hamrogrocery.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {
    lateinit var signupBinding: ActivitySignUpBinding
    lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        signupBinding= ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(signupBinding.root)

        signupBinding.signupBtn.setOnClickListener {
            if (signupBinding.agreeCheckBox.isChecked){
                sharedPreferences=getSharedPreferences("Sign-Up", MODE_PRIVATE)
                val editor=sharedPreferences.edit()
                editor.putString("name",signupBinding.username.text.toString())
                editor.putString("email",signupBinding.phoneNo.text.toString())
                editor.putString("password",signupBinding.password.text.toString())
                editor.apply()
                finish()
            }
            else{
                signupBinding.agreeCheckBox.error="Please agree to terms and conditions"
            }
        }

        signupBinding.SignIn.setOnClickListener {
            var intent=Intent(this@SignUpActivity,LoginActivity::class.java)
            startActivity(intent)
        }

        signupBinding.terms.setOnClickListener {
            var intent=Intent(this@SignUpActivity,ConditionActivity::class.java)
            startActivity(intent)
        }
    }
}