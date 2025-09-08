package com.example.vibezapp10.Models

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.vibezapp10.R

class RecentActivityAdapter(
    private val items: List<RecentActivity>,
    private val onItemClick: (RecentActivity) -> Unit
) : RecyclerView.Adapter<RecentActivityAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleText: TextView = view.findViewById(R.id.activity_title)
        val timestampText: TextView = view.findViewById(R.id.activity_timestamp)

        init {
            view.setOnClickListener {
                onItemClick(items[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recent_activity, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val activity = items[position]
        holder.titleText.text = activity.title
        holder.timestampText.text = activity.timestamp
    }
}
