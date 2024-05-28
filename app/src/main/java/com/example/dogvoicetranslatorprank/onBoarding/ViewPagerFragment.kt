package com.example.dogvoicetranslatorprank.onBoarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.dogvoicetranslatorprank.R
import com.example.dogvoicetranslatorprank.onBoarding.screens.FirstScreen
import com.example.dogvoicetranslatorprank.onBoarding.screens.FourthScreen
import com.example.dogvoicetranslatorprank.onBoarding.screens.SecondScreen
import com.example.dogvoicetranslatorprank.onBoarding.screens.ThirdScreen

class ViewPagerFragment : Fragment() {

    lateinit var  viewPager: ViewPager2
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       val view = inflater.inflate(R.layout.fragment_view_pager, container, false)
        viewPager = view.findViewById(R.id.viewPager)

        val fragmentList = arrayListOf<Fragment>(
            FirstScreen(),
            SecondScreen(),
            ThirdScreen(),
            FourthScreen()
        )

        val adapter = ViewPagerAdapter(
            fragmentList,
            requireActivity().supportFragmentManager,
            lifecycle
        )

        viewPager.adapter = adapter
        return view
    }

}