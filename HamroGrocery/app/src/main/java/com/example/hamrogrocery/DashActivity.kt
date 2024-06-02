package com.example.hamrogrocery

import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.viewpager2.widget.ViewPager2
import com.example.hamrogrocery.adapters.DashViewPagerAdapter
import com.example.hamrogrocery.databinding.ActivityDashBinding
import com.example.hamrogrocery.fragments.HistoryFragment
import com.example.hamrogrocery.fragments.HomeFragment
import com.example.hamrogrocery.fragments.ListFragment
import com.example.hamrogrocery.fragments.ProfileFragment
import com.example.hamrogrocery.fragments.ShopFragment
import com.example.hamrogrocery.vendorActivity.VendorSignUp
import com.google.android.material.navigation.NavigationView

class DashActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    lateinit var dashBinding: ActivityDashBinding
    lateinit var viewPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dashBinding = ActivityDashBinding.inflate(layoutInflater)
        setContentView(dashBinding.root)

        viewPager = findViewById(R.id.viewPager)
        viewPager.adapter = DashViewPagerAdapter(this)

        setSupportActionBar(dashBinding.toolbar)
        val toggle = ActionBarDrawerToggle(
            this,
            dashBinding.drawerlayout,
            dashBinding.toolbar,
            R.string.nav_open,
            R.string.nav_close
        )
        dashBinding.drawerlayout.addDrawerListener(toggle)
        toggle.syncState()
        dashBinding.navigationDrawer.setNavigationItemSelectedListener(this)

        dashBinding.navMenu.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> replaceFragment(0)
                R.id.shop -> replaceFragment(1)
                R.id.list -> replaceFragment(2)
                R.id.history -> replaceFragment(3)
                R.id.profile -> replaceFragment(4)
                else -> {}
            }

            true
        }

        val colorStateList = ColorStateList(
            arrayOf(
                intArrayOf(android.R.attr.state_selected),
                intArrayOf(-android.R.attr.state_selected)
            ),
            intArrayOf(
                resources.getColor(R.color.grey),
                resources.getColor(R.color.white)
            )
        )

        dashBinding.navMenu.itemIconTintList = colorStateList
        dashBinding.navMenu.itemTextColor = colorStateList

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                dashBinding.navMenu.menu.getItem(position).isChecked = true
            }
        })
    }

    private fun replaceFragment(position: Int) {
        viewPager.currentItem = position
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.vendorSign -> {
                var intent=Intent(this@DashActivity,VendorSignUp::class.java)
                startActivity(intent)
                dashBinding.navMenu.itemTextAppearanceActive
            }

            R.id.settings -> {
                replaceFragment(3)
                dashBinding.navMenu.itemTextAppearanceActive
            }
        }

        dashBinding.drawerlayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        if (dashBinding.drawerlayout.isDrawerOpen(GravityCompat.START)) {
            dashBinding.drawerlayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun swipeLeft() {
        val nextPosition = (viewPager.currentItem + 1) % 5
        replaceFragment(nextPosition)
    }

    private fun swipeRight() {
        val previousPosition = (viewPager.currentItem - 1 + 5) % 5
        replaceFragment(previousPosition)
    }
}




