package com.example.hamrogrocery.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.hamrogrocery.fragments.HistoryFragment
import com.example.hamrogrocery.fragments.HomeFragment
import com.example.hamrogrocery.fragments.ListFragment
import com.example.hamrogrocery.fragments.ProfileFragment
import com.example.hamrogrocery.fragments.ShopFragment

class DashViewPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int = 5

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> HomeFragment()
            1 -> ShopFragment()
            2 -> ListFragment()
            3 -> HistoryFragment()
            4 -> ProfileFragment()
            else -> throw IllegalArgumentException("Invalid position: $position")
        }
    }
}