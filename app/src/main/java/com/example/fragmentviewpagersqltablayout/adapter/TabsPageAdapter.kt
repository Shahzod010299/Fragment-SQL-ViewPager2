package com.example.fragmentviewpagersqltablayout.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.fragmentviewpagersqltablayout.ui.AddUserFragment
import com.example.fragmentviewpagersqltablayout.ui.GetUserFragment

class TabsPageAdapter(fm: FragmentManager,lifecycle: Lifecycle,private val  numberOfTabs: Int): FragmentStateAdapter(fm,lifecycle) {

    override fun getItemCount(): Int {
        return numberOfTabs
    }

    override fun createFragment(position: Int): Fragment {
        when(position){
            0 -> return AddUserFragment()
            1 -> return GetUserFragment()
            else -> return AddUserFragment()
        }
    }
}