package com.example.dogvoicetranslatorprank.Fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dogvoicetranslatorprank.APICalling.FakeCall.IncomingCallActivity
import com.example.dogvoicetranslatorprank.APICalling.FakeCall.IncomingCallActivity2
import com.example.dogvoicetranslatorprank.APICalling.FakeCall.IncomingCallActivity3
import com.example.dogvoicetranslatorprank.APICalling.FakeCall.IncomingCallActivity4
import com.example.dogvoicetranslatorprank.Adapter.DogListAdapter
import com.example.dogvoicetranslatorprank.DashboardActivity
import com.example.dogvoicetranslatorprank.R

class FakeCallFragment : Fragment() {

    lateinit var recyclerView: RecyclerView
    lateinit var Homebtn: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_fake_call, container, false)

        recyclerView = view.findViewById(R.id.rv)
        Homebtn = view.findViewById(R.id.home)
        Homebtn.setOnClickListener {
            val intent = Intent(requireActivity(), DashboardActivity::class.java)
            startActivity(intent)
        }


        var Images = intArrayOf(
            R.drawable.husky,
            R.drawable.golden,
            R.drawable.labrador,
            R.drawable.shitzu,
        )
        var nameList = arrayOf("Husky","Golden","Labrador","Shitzu")

        var videoCall = intArrayOf(
            R.drawable.baseline_videocam_24,
            R.drawable.baseline_videocam_24,
            R.drawable.baseline_videocam_24,
            R.drawable.baseline_videocam_24

        )

        val activities: Array<Class<*>> = arrayOf(
            IncomingCallActivity::class.java,
            IncomingCallActivity2::class.java,
            IncomingCallActivity3::class.java,
            IncomingCallActivity4::class.java

        )

        val linearLayoutManager = LinearLayoutManager(requireContext())
        recyclerView.layoutManager = linearLayoutManager

        val recylerViewAdapter = DogListAdapter(requireContext(),Images,nameList,videoCall,activities)
        recyclerView.adapter = recylerViewAdapter
        return view
    }
}