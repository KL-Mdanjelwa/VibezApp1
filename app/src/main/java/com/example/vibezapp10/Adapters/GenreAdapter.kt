package com.example.vibezapp10.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.vibezapp10.Models.Genre
import com.example.vibezapp10.R

class GenreAdapter(
    private val genres: List<Genre>,
    private val onGenreClick: (Genre) -> Unit
) : RecyclerView.Adapter<GenreAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameText: TextView = view.findViewById(R.id.genre_name)
        val icon: ImageView = view.findViewById(R.id.genre_icon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_genre, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val genre = genres[position]
        holder.nameText.text = genre.name
        holder.icon.setImageResource(genre.iconRes)

        holder.itemView.setOnClickListener {
            onGenreClick(genre)
        }
    }

    override fun getItemCount() = genres.size
}
