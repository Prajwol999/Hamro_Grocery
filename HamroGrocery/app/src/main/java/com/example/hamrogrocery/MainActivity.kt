package com.example.hamrogrocery

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.hamrogrocery.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var  mainBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        Handler(Looper.getMainLooper()).postDelayed({
            var intent= Intent(this@MainActivity,LandActivity::class.java)
            startActivity(intent)
            finish()
        },3000)

    }
}