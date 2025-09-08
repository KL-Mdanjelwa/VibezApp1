package com.example.vibezapp10.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.vibezapp10.R

class RecentSearchAdapter(
    private val recentSearches: List<String>,
    private val onSearchClick: (String) -> Unit
) : RecyclerView.Adapter<RecentSearchAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val queryText: TextView = view.findViewById(R.id.recent_query)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recent_search, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val query = recentSearches[position]
        holder.queryText.text = query

        holder.itemView.setOnClickListener {
            onSearchClick(query)
        }
    }

    override fun getItemCount() = recentSearches.size
}
