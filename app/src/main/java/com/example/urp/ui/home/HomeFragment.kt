package com.example.urp.ui.home

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.urp.MainActivity
import com.example.urp.R

class HomeFragment : Fragment() {
    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        val scoreText = root.findViewById<TextView>(R.id.score)
        val walk1m = root.findViewById<Button>(R.id.point1)
        walk1m.setOnClickListener { scoreText.text = "${++MainActivity.score}" }

        val walk5m = root.findViewById<Button>(R.id.point5)
        walk5m.setOnClickListener {
            MainActivity.score += 5
            scoreText.text = "${MainActivity.score}"
        }

        val walk10m = root.findViewById<Button>(R.id.point10)
        walk10m.setOnClickListener {
            MainActivity.score += 10
            scoreText.text = "${MainActivity.score}"
        }

        scoreText.text = "${MainActivity.score}"

        // Arrows for moving
        MainActivity.activity = 0

        val rightArrow = root.findViewById<ImageView>(R.id.rightArrow)
        rightArrow.setOnClickListener {
            val imageView = root.findViewById<ImageView>(R.id.imageView)
            val point1Button = root.findViewById<Button>(R.id.point1)
            val point5Button = root.findViewById<Button>(R.id.point5)
            val point10Button = root.findViewById<Button>(R.id.point10)
            val activityName = root.findViewById<TextView>(R.id.activityName)
            when (MainActivity.activity++) {
                0 -> {
                    imageView.setImageResource(R.drawable.running)
                    point1Button.text = "Walk 1M"
                    point5Button.text = "Walk 5M"
                    point10Button.text = "Walk 10M"
                    activityName.text = "Go out and walk!"
                }
                1 -> {
                    imageView.setImageResource(R.drawable.glass_of_water)
                    point1Button.text = "Drink 1 Cup"
                    point5Button.text = "Drink 5 Cups"
                    point10Button.text = "Drink 10 Cups"
                    activityName.text = "Drink lots of water!"
                }
                2 -> {
                    imageView.setImageResource(R.drawable.boy_doing_pushups)
                    point1Button.text = "Do 5 Pushup"
                    point5Button.text = "Do 25 Pushup"
                    point10Button.text = "Do 50 Pushup"
                    activityName.text = "Do some pushups!"
                }
                3 -> {
                    imageView.setImageResource(R.drawable.sit_up)
                    point1Button.text = "Do 5 Situps"
                    point5Button.text = "Do 25 Situps"
                    point10Button.text = "Do 50 Situps"
                    activityName.text = "Do some situps!"
                }
                4 -> {
                    imageView.setImageResource(R.drawable.stretch)
                    point1Button.text = "Stretch 30 secs"
                    point5Button.text = "Stretch 1 min"
                    point10Button.text = "Stretch 2 mins"
                    activityName.text = "Don't forget to stretch!"
                    MainActivity.activity = 0
                }
            }
        }

        val leftArrow = root.findViewById<ImageView>(R.id.leftArrow)
        leftArrow.setOnClickListener {
            val imageView = root.findViewById<ImageView>(R.id.imageView)
            val point1Button = root.findViewById<Button>(R.id.point1)
            val point5Button = root.findViewById<Button>(R.id.point5)
            val point10Button = root.findViewById<Button>(R.id.point10)
            val activityName = root.findViewById<TextView>(R.id.activityName)
            when (MainActivity.activity--) {
                0 -> {
                    imageView.setImageResource(R.drawable.running)
                    point1Button.text = "Walk 1M"
                    point5Button.text = "Walk 5M"
                    point10Button.text = "Walk 10M"
                    activityName.text = "Go out and walk!"
                    MainActivity.activity = 4
                }
                1 -> {
                    imageView.setImageResource(R.drawable.glass_of_water)
                    point1Button.text = "Drink 1 Cup"
                    point5Button.text = "Drink 5 Cups"
                    point10Button.text = "Drink 10 Cups"
                    activityName.text = "Drink lots of water!"
                }
                2 -> {
                    imageView.setImageResource(R.drawable.boy_doing_pushups)
                    point1Button.text = "Do 5 Pushup"
                    point5Button.text = "Do 25 Pushup"
                    point10Button.text = "Do 50 Pushup"
                    activityName.text = "Do some pushups!"
                }
                3 -> {
                    imageView.setImageResource(R.drawable.sit_up)
                    point1Button.text = "Do 5 Situps"
                    point5Button.text = "Do 25 Situps"
                    point10Button.text = "Do 50 Situps"
                    activityName.text = "Do some situps!"
                }
                4 -> {
                    imageView.setImageResource(R.drawable.stretch)
                    point1Button.text = "Stretch 30 secs"
                    point5Button.text = "Stretch 1 min"
                    point10Button.text = "Stretch 2 mins"
                    activityName.text = "Don't forget to stretch!"
                }
            }
        }

        val levelinfo = root.findViewById<TextView>(R.id.levelInfo)
        levelinfo.text = "Level: ${(MainActivity.score/10).toInt()}"

        scoreText.doOnTextChanged { text, start, before, count ->
            levelinfo.text = "Level: ${(MainActivity.score/10).toInt()}"
        }

        val newDay = root.findViewById<Button>(R.id.newDay)
        val previousDayText = root.findViewById<TextView>(R.id.previousDayText)
        newDay.setOnClickListener {
            previousDayText.text = "You reached level ${(MainActivity.score/10).toInt()} yesterday!"
            MainActivity.score = 0
            scoreText.text = "${MainActivity.score}"
            levelinfo.text = "Level: 0"
        }

        return root
    }
}
