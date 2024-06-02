package com.example.hamrogrocery.vendorActivity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.hamrogrocery.R
import com.example.hamrogrocery.databinding.ActivityVendorSignUpBinding

class VendorSignUp : AppCompatActivity() {
    lateinit var vendorSignUpBinding: ActivityVendorSignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vendorSignUpBinding = ActivityVendorSignUpBinding.inflate(layoutInflater)
        setContentView(vendorSignUpBinding.root)


    }
}