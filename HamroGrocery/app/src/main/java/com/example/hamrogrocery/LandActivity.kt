package com.example.hamrogrocery

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.hamrogrocery.adapters.ImageAdapter
import com.example.hamrogrocery.databinding.ActivityLandBinding

class LandActivity : AppCompatActivity() {
    lateinit var landBinding: ActivityLandBinding
    var imgList= ArrayList<Int>()
    lateinit var adapter: ImageAdapter
    lateinit var viewPager2: ViewPager2
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        landBinding = ActivityLandBinding.inflate(layoutInflater)
        setContentView(landBinding.root)
        landBinding.root

        viewPager2=landBinding.viewpager2
        imgList.add(R.drawable.queuepager)
        imgList.add(R.drawable.deliverypager)
        imgList.add(R.drawable.parkingpager)
        imgList.add(R.drawable.cardpager)
        imgList.add(R.drawable.startpager)

        adapter= ImageAdapter(imgList, viewPager2)
        landBinding.viewpager2.adapter=adapter

        landBinding.skip.setOnClickListener{
            var intent= Intent(this@LandActivity,DashActivity::class.java)
            startActivity(intent)
            finish()
        }

        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                if (position == 0) {
                    landBinding.prev.visibility = View.GONE
                } else {
                    landBinding.prev.visibility = View.VISIBLE
                }

                if (position == imgList.size - 1) {
                    landBinding.next.visibility = View.GONE
                    landBinding.skip.text = "Get Started"
                } else {
                    landBinding.next.visibility = View.VISIBLE
                    landBinding.skip.text = "Skip"
                }
            }
        })

        landBinding.next.setOnClickListener {
            val nextPageIndex = viewPager2.currentItem + 1
            viewPager2.setCurrentItem(nextPageIndex, true)
        }

        landBinding.prev.setOnClickListener {
            val prevPageIndex = viewPager2.currentItem - 1
            viewPager2.setCurrentItem(prevPageIndex, true)
        }


    }
}
