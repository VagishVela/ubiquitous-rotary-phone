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
            when (MainActivity.activity++) {
                0 -> {
                    imageView.setImageResource(R.drawable.waterlogo)
                }
                1 -> {
                    imageView.setImageResource(R.drawable.bottledwater)
                }
                2 -> {
                    imageView.setImageResource(R.drawable.running_man)
                    MainActivity.activity = 0
                }
            }
        }

        val leftArrow = root.findViewById<ImageView>(R.id.leftArrow)
        leftArrow.setOnClickListener {
            val imageView = root.findViewById<ImageView>(R.id.imageView)
            when (MainActivity.activity--) {
                0 -> {
                    imageView.setImageResource(R.drawable.bottledwater)
                    MainActivity.activity = 2 // This will change to max num of activities.
                }
                1 -> {
                    imageView.setImageResource(R.drawable.running_man)
                }
                2 -> {
                    imageView.setImageResource(R.drawable.waterlogo)
                }
            }
        }

        return root
    }
}
