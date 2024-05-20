package com.example.hamrogrocery.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.hamrogrocery.R
import com.example.hamrogrocery.SignUpActivity
import com.example.hamrogrocery.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {
    lateinit var profileBinding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       profileBinding= FragmentProfileBinding.inflate(layoutInflater)
        profileBinding.register.setOnClickListener {
            var intent=Intent(activity,SignUpActivity::class.java)
            startActivity(intent)
        }
        return profileBinding.root
    }

}