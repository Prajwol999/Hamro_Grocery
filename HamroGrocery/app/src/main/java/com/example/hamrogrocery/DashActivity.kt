package com.example.hamrogrocery

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.provider.CalendarContract.Colors
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.hamrogrocery.databinding.ActivityDashBinding
import com.example.hamrogrocery.fragments.HistoryFragment
import com.example.hamrogrocery.fragments.HomeFragment
import com.example.hamrogrocery.fragments.ListFragment
import com.example.hamrogrocery.fragments.ProfileFragment
import com.example.hamrogrocery.fragments.ShopFragment

class DashActivity : AppCompatActivity() {
    lateinit var dashBinding: ActivityDashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dashBinding = ActivityDashBinding.inflate(layoutInflater)
        setContentView(dashBinding.root)


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
                Color.WHITE
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



}