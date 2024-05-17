package com.example.hamrogrocery

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.hamrogrocery.adapters.ViewPagerAdapter
import com.example.hamrogrocery.databinding.ActivityLandingBinding
import me.relex.circleindicator.CircleIndicator3

class LandingActivity : AppCompatActivity() {
    lateinit var landbinding: ActivityLandingBinding
    var imageList = mutableListOf<Int>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        landbinding = ActivityLandingBinding.inflate(layoutInflater)
        setContentView(landbinding.root)



        landbinding.viewpager2.adapter = ViewPagerAdapter(imageList)
        landbinding.viewpager2.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        val indicator = findViewById<CircleIndicator3>(R.id.indicator)
        indicator.setViewPager(landbinding.viewpager2)

        landbinding.skipBtn.setOnClickListener {
            val intent = Intent(this@LandingActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        addtolist(R.drawable.cardpager)
        addtolist(R.drawable.deliverypager)
        addtolist(R.drawable.parkingpager)
        addtolist(R.drawable.queuepager)
        addtolist(R.drawable.startpager)

        posttolist()
        }
        private fun addtolist(img:Int){
            imageList.add(img)
        }

        private fun posttolist(){
            for(i in 1..5){
                addtolist(R.mipmap.ic_launcher)
            }
        }

    }
