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

class ProfileFragment : Fragment() {
    lateinit var profileBinding: FragmentProfileBinding
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       profileBinding= FragmentProfileBinding.inflate(layoutInflater)

        profileBinding.register.setOnClickListener {
            var intent=Intent(activity,SignUpActivity::class.java)
            startActivity(intent)
        }

        profileBinding.forgotPassword.setOnClickListener {
            var intent=Intent(activity, ForgetActivity::class.java)
            startActivity(intent)
        }

        profileBinding.signinBtn.setOnClickListener {
            sharedPreferences=requireActivity().getSharedPreferences("Sign-Up",Context.MODE_PRIVATE)
            var name=sharedPreferences.getString("name","")
            var email=sharedPreferences.getString("phone","")
            var pass= sharedPreferences.getString("pass","")
            if(profileBinding.userField.text.toString()==name || profileBinding.userField.text.toString()==email && profileBinding.passfield.text.toString()==pass){
                var intent=Intent(activity,DashActivity::class.java)
                startActivity(intent)
            }else{
                Toast.makeText(activity,"Invalid Credentials",Toast.LENGTH_SHORT).show()
        }

        }


        return profileBinding.root
    }

}