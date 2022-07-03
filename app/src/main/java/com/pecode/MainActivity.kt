package com.pecode

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.*
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2

class MainActivity : AppCompatActivity() {
    lateinit var viewPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            viewPager = findViewById(R.id.pager)
            allFragments.fragmentsList.add(MainFragment())

            val pagerAdapter = ScreenSlidePagerAdapter(supportFragmentManager)
            allFragments.pagerAdapter.add(pagerAdapter)
            viewPager.adapter = allFragments.pagerAdapter[0]
        }
    }

}
