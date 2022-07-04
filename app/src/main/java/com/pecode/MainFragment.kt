package com.pecode

import android.app.PendingIntent
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.fragment.app.Fragment


class MainFragment : Fragment() {
    private lateinit var countText: TextView
    private lateinit var plusImg: ImageView
    private lateinit var minusImg: ImageView
    private lateinit var lineMinus: ImageView
    private lateinit var createNotBtn: TextView
    private val fragmentCount: Int = allFragments.count

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        countText = getView()?.findViewById(R.id.count_text) as TextView
        plusImg = getView()?.findViewById(R.id.plus_img) as ImageView
        minusImg = getView()?.findViewById(R.id.minus_img) as ImageView
        lineMinus = getView()?.findViewById(R.id.line3_img) as ImageView
        createNotBtn = getView()?.findViewById(R.id.create_notification_text) as TextView

        countText.text = fragmentCount.toString()

        if (fragmentCount == 1) {
            minusImg.visibility = View.INVISIBLE
            lineMinus.visibility = View.INVISIBLE
        } else {
            minusImg.visibility = View.VISIBLE
            lineMinus.visibility = View.VISIBLE
        }


        plusImg.setOnClickListener {

            allFragments.count++
            allFragments.fragmentsList.add(MainFragment())



            allFragments.pagerAdapter[0].notifyDataSetChanged()
            (activity as MainActivity).viewPager.setCurrentItem(allFragments.count, true)
        }

        minusImg.setOnClickListener {
            allFragments.count--
            (activity as MainActivity).viewPager.setCurrentItem(allFragments.count-1, true)
            NotificationManagerCompat.from(requireActivity()).cancel(allFragments.fragmentsList.size)
            allFragments.fragmentsList.remove(allFragments.fragmentsList.last())
            allFragments.pagerAdapter[0].notifyDataSetChanged()
        }



        createNotBtn.setOnClickListener(View.OnClickListener {

            val channelId = "channelId"
            val description = "Notification $fragmentCount"
            val headline = "Chat heads active"

            val notificationIntent = Intent(context, MainActivity::class.java)
            notificationIntent.putExtra("fragmentNumber", fragmentCount)

            notificationIntent.addCategory(Intent.CATEGORY_LAUNCHER)

            val pendingIntent = PendingIntent.getActivity(context, 0, notificationIntent, PendingIntent.FLAG_MUTABLE)

            val notificationManager = NotificationCompat.Builder(requireActivity(), channelId)
                .setContentTitle(headline)
                .setContentText(description)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentIntent(pendingIntent)
                .build()
            NotificationManagerCompat.from(requireActivity()).apply {
                notify(fragmentCount, notificationManager)
            }
        })
    }
}
