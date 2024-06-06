package com.example.dogvoicetranslatorprank.Fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageButton
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dogvoicetranslatorprank.APICalling.Sounds.SoundsPojo
import com.example.dogvoicetranslatorprank.Adapter.SoundAdapter
import com.example.dogvoicetranslatorprank.DashboardActivity
import com.example.dogvoicetranslatorprank.R
import com.example.dogvoicetranslatorprank.RetroFitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DogSoundsFragment : Fragment() {

    lateinit var Homebtn: ImageView
    lateinit var recyclerView: RecyclerView
    lateinit var SearchButton: AppCompatImageButton
    lateinit var searchEditText: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_dog_sounds, container, false)
        Homebtn = view.findViewById(R.id.home)
        recyclerView = view.findViewById(R.id.rv)
        SearchButton = view.findViewById(R.id.searchButton)
        searchEditText = view.findViewById(R.id.searchEditText)



        Homebtn.setOnClickListener {
            val intent = Intent(requireActivity(), DashboardActivity::class.java)
            startActivity(intent)
        }

        getData()
        return view
    }

    private fun getData() {
        RetroFitInstance.apiInterface.Sounds().enqueue(object : Callback<SoundsPojo?> {
            override fun onResponse(call: Call<SoundsPojo?>, response: Response<SoundsPojo?>) {
                if (isAdded) { // Check if fragment is still attached
                    if (response.isSuccessful && response.body() != null) {
                        val gridLayoutManager = GridLayoutManager(requireActivity(), 2)
                        recyclerView.layoutManager = gridLayoutManager

                        val soundAdapter = SoundAdapter(requireContext(), response.body()!!)
                        recyclerView.adapter = soundAdapter

                        SearchButton.setOnClickListener {
                            val query = searchEditText.text.toString().trim()
                            soundAdapter.performSearch(query)
                        }

                        Toast.makeText(requireContext(), "Success", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            override fun onFailure(call: Call<SoundsPojo?>, t: Throwable) {
                if (isAdded) { // Check if fragment is still attached
                    Toast.makeText(requireContext(), "Error: " + t.message, Toast.LENGTH_SHORT)
                        .show()
                }
            }
        })
    }
}