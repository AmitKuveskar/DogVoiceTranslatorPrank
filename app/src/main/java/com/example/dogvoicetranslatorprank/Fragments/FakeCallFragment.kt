package com.example.dogvoicetranslatorprank.Fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import com.example.dogvoicetranslatorprank.APICalling.FakeCall.IncomingCallActivity
import com.example.dogvoicetranslatorprank.R

class FakeCallFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_fake_call, container, false)
        val incomingCallButton = view.findViewById<ImageView>(R.id.videocall)
        incomingCallButton.setOnClickListener {
            val intent = Intent(requireContext(), IncomingCallActivity::class.java)
            startActivity(intent)


        }
        return view
    }
}