package com.example.dogvoicetranslatorprank.onBoarding.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager2.widget.ViewPager2
import com.example.dogvoicetranslatorprank.R

class ThirdScreen : Fragment() {
    lateinit var  nextbtn2 : ImageView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_third_screen, container, false)
        nextbtn2 = view.findViewById(R.id.nextbtn)
        val viewPager = activity?.findViewById<ViewPager2>(R.id.viewPager)

        nextbtn2.setOnClickListener {
            viewPager?.currentItem = 3
        }
        return  view
    }

}