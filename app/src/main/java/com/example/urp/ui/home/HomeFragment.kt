package com.example.urp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.urp.R

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        val score = root.findViewById<TextView>(R.id.score)
        val walk1m = root.findViewById<Button>(R.id.walk1m)
        walk1m.setOnClickListener { testFun(root) }
        return root
    }

    fun testFun(view: View) {
//        Toast.makeText(applicationContext, "Simple Button 1", Toast.LENGTH_LONG).show()
        val walk1m = view.findViewById<Button>(R.id.walk1m)
        walk1m.text = "${walk1m.text} 1"
    }
}