package com.example.dogvoicetranslatorprank.Fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dogvoicetranslatorprank.APICalling.Tranings.TraningsPojo
import com.example.dogvoicetranslatorprank.Adapter.TrainingAdapter
import com.example.dogvoicetranslatorprank.DashboardActivity
import com.example.dogvoicetranslatorprank.R
import com.example.dogvoicetranslatorprank.RetroFitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TrainingFragment : Fragment() {

    lateinit var recyclerView: RecyclerView
    lateinit var Homebtn: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_training, container, false)

        recyclerView = view.findViewById(R.id.rv)

        Homebtn = view.findViewById(R.id.home)
        Homebtn.setOnClickListener {
            val intent = Intent(requireActivity(), DashboardActivity::class.java)
            startActivity(intent)
        }
        getData()
        return view
    }


    private fun getData() {
        RetroFitInstance.apiInterface.Training().enqueue(object : Callback<TraningsPojo?> {
            override fun onResponse(call: Call<TraningsPojo?>, response: Response<TraningsPojo?>) {
                if (isAdded) { // Check if fragment is still attached
                    if (response.isSuccessful && response.body() != null) {
                        val linearLayoutManager = LinearLayoutManager(requireContext())
                        recyclerView.layoutManager = linearLayoutManager

                        val trainingAdapter = TrainingAdapter(requireActivity(), response.body()!!)
                        recyclerView.adapter = trainingAdapter

                        Toast.makeText(requireContext(), "Success", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            override fun onFailure(call: Call<TraningsPojo?>, t: Throwable) {
                if (isAdded) { // Check if fragment is still attached
                    Toast.makeText(requireContext(), "Error: " + t.message, Toast.LENGTH_SHORT)
                        .show()
                }
            }
        })
    }
}