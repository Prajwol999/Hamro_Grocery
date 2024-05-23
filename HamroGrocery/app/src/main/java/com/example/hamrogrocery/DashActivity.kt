package com.example.hamrogrocery

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
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
                R.id.home -> replaceFragment(HomeFragment())
                R.id.shop -> replaceFragment(ShopFragment())
                R.id.list -> replaceFragment(ListFragment())
                R.id.history -> replaceFragment(HistoryFragment())
                R.id.profile -> replaceFragment(ProfileFragment())
                else -> {}
            }
            true
        }
    }
    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager: FragmentManager =supportFragmentManager
        val fragmentTransaction: FragmentTransaction =fragmentManager.beginTransaction()

        fragmentTransaction.replace(R.id.frameLayout,fragment)
        fragmentTransaction.commit()
    }

}