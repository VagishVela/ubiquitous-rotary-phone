package com.example.urp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
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
//        val textView: TextView = root.findViewById(R.id.text_home)
//        homeViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })

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
        val rightArrow = root.findViewById<ImageView>(R.id.rightArrow)
        rightArrow.setOnClickListener {
            val imageView = root.findViewById<ImageView>(R.id.imageView)
            val point1Button = root.findViewById<Button>(R.id.point1)
            val point5Button = root.findViewById<Button>(R.id.point5)
            val point10Button = root.findViewById<Button>(R.id.point10)
            when (MainActivity.activity++) {
                0 -> {
                    imageView.setImageResource(R.drawable.pushup)
                    point1Button.text = "Do 5 Pushup"
                    point5Button.text = "Do 25 Pushup"
                    point10Button.text = "Do 50 Pushup"
                }
                1 -> {
                    imageView.setImageResource(R.drawable.bottledwater)
                    point1Button.text = "Drank 1 Cup"
                    point5Button.text = "Drank 5 Cups"
                    point10Button.text = "Drank 10 Cups"
                }
                2 -> {
                    imageView.setImageResource(R.drawable.running_man)
                    point1Button.text = "Walk 1M"
                    point5Button.text = "Walk 5M"
                    point10Button.text = "Walk 10M"
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
            when (MainActivity.activity--) {
                0 -> {
                    imageView.setImageResource(R.drawable.bottledwater)
                    MainActivity.activity = 2 // This will change to max num of activities.
                    point1Button.text = "Drank 1 Cup"
                    point5Button.text = "Drank 5 Cups"
                    point10Button.text = "Drank 10 Cups"
                }
                1 -> {
                    imageView.setImageResource(R.drawable.running_man)
                    point1Button.text = "Walk 1M"
                    point5Button.text = "Walk 5M"
                    point10Button.text = "Walk 10M"
                }
                2 -> {
                    imageView.setImageResource(R.drawable.pushup)
                    point1Button.text = "Do 5 Pushup"
                    point5Button.text = "Do 25 Pushup"
                    point10Button.text = "Do 50 Pushup"
                }
            }
        }

        return root
    }
}
