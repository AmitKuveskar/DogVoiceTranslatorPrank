package com.example.dogvoicetranslatorprank.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dogvoicetranslatorprank.APICalling.Tranings.TraningsPojo
import com.example.dogvoicetranslatorprank.R

class TrainingAdapter (context: Context, private val TrainingPojo: TraningsPojo) : RecyclerView.Adapter<TrainingAdapter.ViewHolder>() {
    class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){
        val DogImage : ImageView = itemView.findViewById(R.id.Dog_Image)
        val Title : TextView = itemView.findViewById(R.id.Title)
        val Description : TextView = itemView.findViewById(R.id.Description)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.traning_row,parent,false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val Training = TrainingPojo[position]
        holder.Title.text = Training.title
        holder.Description.text = Training.description
        Glide.with(holder.itemView)
            .load(Training.post_image) // Load image from URL
            .into(holder.DogImage) // Set image into ImageView


    }

    override fun getItemCount(): Int {
        return  TrainingPojo.size
    }
}
