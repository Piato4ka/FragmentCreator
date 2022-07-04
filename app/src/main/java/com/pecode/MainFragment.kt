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
    private val fragmentCount: Int = databaseImit.count

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
            databaseImit.count++
            databaseImit.fragmentsList.add(MainFragment())
            databaseImit.pagerAdapter[0].notifyDataSetChanged()
            (activity as MainActivity).viewPager.setCurrentItem(databaseImit.count, true)
        }

        minusImg.setOnClickListener {
            databaseImit.count--
            (activity as MainActivity).viewPager.setCurrentItem(databaseImit.count - 1, true)
            NotificationManagerCompat.from(requireActivity())
                .cancel(databaseImit.fragmentsList.size)
            databaseImit.fragmentsList.remove(databaseImit.fragmentsList.last())
            databaseImit.pagerAdapter[0].notifyDataSetChanged()
        }

        createNotBtn.setOnClickListener(View.OnClickListener {
            val description = "Notification $fragmentCount"
            val headline = "Chat heads active"

            val notificationIntent = Intent(context, MainActivity::class.java)
            notificationIntent.putExtra("fragmentNumber", fragmentCount)

            notificationIntent.addCategory(Intent.CATEGORY_LAUNCHER)

            val pendingIntent =
                PendingIntent.getActivity(
                    context,
                    fragmentCount,
                    notificationIntent,
                    PendingIntent.FLAG_MUTABLE
                )

            val notificationManager = NotificationCompat.Builder(requireActivity())
                .setChannelId(getString(R.string.channel_name))
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