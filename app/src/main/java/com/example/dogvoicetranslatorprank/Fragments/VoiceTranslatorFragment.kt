package com.example.dogvoicetranslatorprank.Fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.dogvoicetranslatorprank.DashboardActivity
import com.example.dogvoicetranslatorprank.R


class VoiceTranslatorFragment : Fragment() {
    lateinit var Homebtn: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_voice_translator, container, false)
        Homebtn = view.findViewById(R.id.home)
        Homebtn.setOnClickListener {
            val intent = Intent(requireActivity(), DashboardActivity::class.java)
            startActivity(intent)

        }
        return view

    }
}