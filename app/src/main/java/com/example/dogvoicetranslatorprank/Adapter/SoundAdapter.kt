package com.example.dogvoicetranslatorprank.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dogvoicetranslatorprank.APICalling.Sounds.SoundsPojo
import com.example.dogvoicetranslatorprank.APICalling.Sounds.SoundsPojoItem
import com.example.dogvoicetranslatorprank.R


class SoundAdapter(private val context: Context, private val SoundsPojo: SoundsPojo) : RecyclerView.Adapter<SoundAdapter.ViewHolder>() {

    private var filteredList: List<SoundsPojoItem> = SoundsPojo

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val Image: de.hdodenhof.circleimageview.CircleImageView = itemView.findViewById(R.id.pic)
        val Title: TextView = itemView.findViewById(R.id.singleText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.sounds_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val sound = filteredList[position]
        holder.Title.text = sound.post_name
        Glide.with(holder.itemView)
            .load(sound.post_image) // Load image from URL
            .into(holder.Image) // Set image into ImageView


    }


    override fun getItemCount(): Int {
        return filteredList.size
    }


    // Method to perform search based on query
    fun performSearch(query: String) {
        if (query.isEmpty()) {
            filteredList = SoundsPojo // Reset to original list if query is empty
        } else {
            filteredList = SoundsPojo.filter { it.post_name.contains(query, ignoreCase = true) }
        }
        notifyDataSetChanged() // Notify adapter that data set has changed
    }
}