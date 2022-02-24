package com.example.fragmentviewpagersqltablayout

import android.graphics.BlendModeColorFilter
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.core.graphics.BlendModeColorFilterCompat
import androidx.core.graphics.BlendModeCompat
import com.example.fragmentviewpagersqltablayout.adapter.TabsPageAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tab_layout.setSelectedTabIndicatorColor(Color.WHITE);
        tab_layout.setBackgroundColor(ContextCompat.getColor(this,R.color.grey))
        tab_layout.tabTextColors = ContextCompat.getColorStateList(this,R.color.white)

        val numberTabs = 2

        tab_layout.tabMode = TabLayout.MODE_FIXED
        tab_layout.isInlineLabel = true

        val adapter = TabsPageAdapter(supportFragmentManager,lifecycle,numberTabs)
        view_pager.adapter = adapter

        view_pager.isUserInputEnabled = true

        TabLayoutMediator(tab_layout,view_pager){ tab, pos ->

            when(pos){
                0 -> {
                   tab.text = "add user"
                   tab.setIcon(R.drawable.ic_baseline_add_circle_24)
                }
                1 -> {
                    tab.text = "get user"
                    tab.setIcon(R.drawable.ic_baseline_info_24)
                }
            }
            tab.icon?.colorFilter = BlendModeColorFilterCompat.createBlendModeColorFilterCompat(
                Color.WHITE,
                BlendModeCompat.SRC_ATOP
            )

        }.attach()

    }
}