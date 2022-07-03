package com.pecode

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData


object allFragments {
    var fragmentsList =  ArrayList<Fragment>()
    var count = 1
    var pagerAdapter = ArrayList<ScreenSlidePagerAdapter>()

}
