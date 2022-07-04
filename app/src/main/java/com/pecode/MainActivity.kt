package com.pecode

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager


class MainActivity : AppCompatActivity() {
    lateinit var viewPager: ViewPager
    private var fragmentNumberEx: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        createNotificationChannel()
        if (savedInstanceState == null) {
            viewPager = findViewById(R.id.pager)

            val pagerAdapter = ScreenSlidePagerAdapter(supportFragmentManager)
            databaseImit.pagerAdapter.add(pagerAdapter)
            viewPager.adapter = databaseImit.pagerAdapter[0]

            fragmentNumberEx = intent.getIntExtra("fragmentNumber", 1)
            createFragments()
            viewPager.setCurrentItem(fragmentNumberEx)
        }
    }

    private fun createFragments() {
        for (i in 1..fragmentNumberEx) {
            databaseImit.count++
            databaseImit.fragmentsList.add(MainFragment())
            databaseImit.pagerAdapter[0].notifyDataSetChanged()
        }
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.channel_name)
            val descriptionText = "getString(R.string.channel_description)"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel =
                NotificationChannel(getString(R.string.channel_name), name, importance).apply {
                    description = descriptionText
                }
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}


