package com.pecode

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import androidx.viewpager.widget.ViewPager


class MainActivity : AppCompatActivity() {
    lateinit var viewPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            viewPager = findViewById(R.id.pager)


            val pagerAdapter = ScreenSlidePagerAdapter(supportFragmentManager)
            allFragments.pagerAdapter.add(pagerAdapter)
            viewPager.adapter = allFragments.pagerAdapter[0]


            var fragmentNumberEx = intent.getIntExtra("fragmentNumber", 1)


            for (i in 1..fragmentNumberEx) {
                allFragments.count++
                allFragments.fragmentsList.add(MainFragment())
                allFragments.pagerAdapter[0].notifyDataSetChanged()
            }
        }
    }
}


