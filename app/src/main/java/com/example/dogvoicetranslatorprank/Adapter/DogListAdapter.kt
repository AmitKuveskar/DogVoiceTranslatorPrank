package com.example.dogvoicetranslatorprank.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.dogvoicetranslatorprank.APICalling.FakeCall.IncomingCallActivity
import com.example.dogvoicetranslatorprank.R


class DogListAdapter(private val context: Context, private val Images: IntArray, private val nameList: Array<String>, private  val videocall: IntArray, private val activities: Array<Class<*>>) :
    RecyclerView.Adapter<DogListAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val img: ImageView = itemView.findViewById(R.id.pic)
        val name: TextView = itemView.findViewById(R.id.dogname)
        val videoicon: ImageView = itemView.findViewById(R.id.videocall)
        val cardView: CardView = itemView.findViewById(R.id.cardview)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.doglist_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = nameList[position]
        holder.img.setImageResource(Images[position])
        holder.videoicon.setImageResource(videocall[position])
        holder.videoicon.setOnClickListener {
            val intent = Intent(context, activities[position])
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return Images.size
    }
}