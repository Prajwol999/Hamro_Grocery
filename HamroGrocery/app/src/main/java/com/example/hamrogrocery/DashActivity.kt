package com.example.hamrogrocery

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.hamrogrocery.databinding.ActivityDashBinding
import com.example.hamrogrocery.fragments.HistoryFragment
import com.example.hamrogrocery.fragments.HomeFragment
import com.example.hamrogrocery.fragments.ListFragment
import com.example.hamrogrocery.fragments.ProfileFragment
import com.example.hamrogrocery.fragments.ShopFragment
import com.google.android.material.navigation.NavigationView

class DashActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    lateinit var dashBinding: ActivityDashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        class DashActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
            lateinit var dashBinding: ActivityDashBinding
            override fun onCreate(savedInstanceState: Bundle?) {
                super.onCreate(savedInstanceState)
                dashBinding = ActivityDashBinding.inflate(layoutInflater)
                setContentView(dashBinding.root)

                setSupportActionBar(dashBinding.toolbar)
                val toggle= ActionBarDrawerToggle(this,dashBinding.drawerlayout,dashBinding.toolbar,R.string.nav_open,R.string.nav_close)
                dashBinding.drawerlayout.addDrawerListener(toggle)
                toggle.syncState()
                dashBinding.navigationDrawer.setNavigationItemSelectedListener(this)


                dashBinding.navMenu.setOnItemSelectedListener {
                    when(it.itemId){
                        R.id.home ->{ replaceFragment(HomeFragment())
                            dashBinding.navMenu.itemTextAppearanceActive
                        }
                        R.id.shop ->{replaceFragment(ShopFragment())
                            dashBinding.navMenu.itemTextAppearanceActive
                        }
                        R.id.list -> {
                            replaceFragment(ListFragment())
                            dashBinding.navMenu.itemTextAppearanceActive
                        }
                        R.id.history -> {
                            replaceFragment(HistoryFragment())
                            dashBinding.navMenu.itemTextAppearanceActive
                        }
                        R.id.profile -> {
                            replaceFragment(ProfileFragment())
                            dashBinding.navMenu.itemTextAppearanceActive
                        }
                        else -> {}
                    }


                    true
                }
                val colorStateList = ColorStateList(
                    arrayOf(
                        intArrayOf(android.R.attr.state_selected), // State when the item is selected
                        intArrayOf(-android.R.attr.state_selected) // State when the item is not selected
                    ),
                    intArrayOf(
                        resources.getColor(R.color.grey),
                        resources.getColor(R.color.white)
                    ))

                dashBinding.navMenu.itemIconTintList = colorStateList
                dashBinding.navMenu.itemTextColor = colorStateList
            }


            private fun replaceFragment(fragment: Fragment) {
                val fragmentManager: FragmentManager =supportFragmentManager
                val fragmentTransaction: FragmentTransaction =fragmentManager.beginTransaction()

                fragmentTransaction.replace(R.id.frameLayout,fragment)
                fragmentTransaction.commit()

            }

            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                when(item.itemId){
                    R.id.profile ->{ replaceFragment(ProfileFragment())
                        dashBinding.navMenu.itemTextAppearanceActive
                    }
                    R.id.settings -> {
                        replaceFragment(HistoryFragment())
                        dashBinding.navMenu.itemTextAppearanceActive
                    }

                }
                dashBinding.drawerlayout.closeDrawer(GravityCompat.START)
                return true
            }

            override fun onBackPressed() {
                if (dashBinding.drawerlayout.isDrawerOpen(GravityCompat.START)){
                    dashBinding.drawerlayout.closeDrawer(GravityCompat.START)
                }
                else{
                    super.onBackPressedDispatcher.onBackPressed()
                }


            }


        }
        dashBinding = ActivityDashBinding.inflate(layoutInflater)
        setContentView(dashBinding.root)

        setSupportActionBar(dashBinding.toolbar)
        val toggle= ActionBarDrawerToggle(this,dashBinding.drawerlayout,dashBinding.toolbar,R.string.nav_open,R.string.nav_close)
        dashBinding.drawerlayout.addDrawerListener(toggle)
        toggle.syncState()
        dashBinding.navigationDrawer.setNavigationItemSelectedListener(this)


        dashBinding.navMenu.setOnItemSelectedListener {
            when(it.itemId){
                R.id.home ->{ replaceFragment(HomeFragment())
                dashBinding.navMenu.itemTextAppearanceActive
                }
                R.id.shop ->{replaceFragment(ShopFragment())
                    dashBinding.navMenu.itemTextAppearanceActive
                }
                R.id.list -> {
                    replaceFragment(ListFragment())
                    dashBinding.navMenu.itemTextAppearanceActive
                }
                R.id.history -> {
                    replaceFragment(HistoryFragment())
                    dashBinding.navMenu.itemTextAppearanceActive
                }
                R.id.profile -> {
                    replaceFragment(ProfileFragment())
                    dashBinding.navMenu.itemTextAppearanceActive
                }
                else -> {}
            }


            true
        }
        val colorStateList = ColorStateList(
            arrayOf(
                intArrayOf(android.R.attr.state_selected), // State when the item is selected
                intArrayOf(-android.R.attr.state_selected) // State when the item is not selected
            ),
            intArrayOf(
                resources.getColor(R.color.grey),
                resources.getColor(R.color.white)
        ))

        dashBinding.navMenu.itemIconTintList = colorStateList
        dashBinding.navMenu.itemTextColor = colorStateList
    }


    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager: FragmentManager =supportFragmentManager
        val fragmentTransaction: FragmentTransaction =fragmentManager.beginTransaction()

        fragmentTransaction.replace(R.id.frameLayout,fragment)
        fragmentTransaction.commit()

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.profile ->{ replaceFragment(ProfileFragment())
                dashBinding.navMenu.itemTextAppearanceActive
            }
            R.id.settings -> {
                replaceFragment(HistoryFragment())
                dashBinding.navMenu.itemTextAppearanceActive
            }

        }
        dashBinding.drawerlayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        if (dashBinding.drawerlayout.isDrawerOpen(GravityCompat.START)){
            dashBinding.drawerlayout.closeDrawer(GravityCompat.START)
        }
        else{
            super.onBackPressedDispatcher.onBackPressed()
        }


    }

    override fun onPause() {
        super.onPause()
        dashBinding.navMenu.itemTextAppearanceActive
    }

    override fun onResume() {
        super.onResume()
        dashBinding.navMenu.itemTextAppearanceActive
    }

}