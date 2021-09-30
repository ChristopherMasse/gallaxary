package com.christophermasse.spaceshowcase.ui.nav

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.christophermasse.spaceshowcase.ui.gallery.GalleryFrag
import com.christophermasse.spaceshowcase.ui.search.SearchFrag

class TabAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return if (position == 1){
            SearchFrag()
        } else{
            GalleryFrag()
        }
    }
}