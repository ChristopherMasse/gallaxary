package com.christophermasse.spaceshowcase.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.widget.ViewPager2
import com.christophermasse.spaceshowcase.databinding.ActivityTabBinding
import com.christophermasse.spaceshowcase.ui.nav.TabAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class TabActivity: FragmentActivity() {

    private lateinit var binding: ActivityTabBinding

    private lateinit var tabLayout: TabLayout

    private lateinit var pager2: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTabBinding.inflate(layoutInflater)
        val root: View = binding.root
        setContentView(root)

        tabLayout = binding.tabLayout
        pager2 = binding.viewpager

        var adapter = TabAdapter(this)
        pager2.adapter = adapter

        TabLayoutMediator(tabLayout, pager2) { tab, position ->
            if (position == 0){
                tab.text = "Gallery"
            } else{
                tab.text = "Search"
            }
        }.attach()

    }
}