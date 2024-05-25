package com.example.hamrogrocery

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.hamrogrocery.databinding.ActivityForgetBinding

class ForgetActivity : AppCompatActivity() {
    lateinit var forgetBinding: ActivityForgetBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        forgetBinding=ActivityForgetBinding.inflate(layoutInflater)
        setContentView(forgetBinding.root)


    }
}