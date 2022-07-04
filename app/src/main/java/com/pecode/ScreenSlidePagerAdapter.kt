package com.pecode

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class ScreenSlidePagerAdapter (fm: FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getCount(): Int = allFragments.fragmentsList.size
    override fun getItem(position: Int): Fragment = allFragments.fragmentsList.get(position)
    }


