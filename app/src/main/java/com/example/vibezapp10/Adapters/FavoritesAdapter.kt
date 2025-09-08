package com.example.vibezapp10.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.vibezapp10.Models.Song
import com.example.vibezapp10.R

class FavoritesAdapter(
    private val songs: MutableList<Song>,
    private val onSongClick: (Song) -> Unit,
    private val onFavoriteClick: (Song) -> Unit
) : RecyclerView.Adapter<FavoritesAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val albumArt: ImageView = view.findViewById(R.id.download_album)
        val title: TextView = view.findViewById(R.id.download_title)
        val artist: TextView = view.findViewById(R.id.download_artist)
        val offlineIndicator: ImageView = view.findViewById(R.id.download_offline)
        val favoriteBtn: ImageButton = view.findViewById(R.id.btn_favorite) // optional if you want toggle
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_download, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val song = songs[position]
        holder.title.text = song.title
        holder.artist.text = song.artist
        // Load album art (Glide/Picasso)
        // Glide.with(holder.itemView).load(song.albumArt).into(holder.albumArt)
        holder.offlineIndicator.visibility = if (song.localPath != null) View.VISIBLE else View.GONE

        holder.itemView.setOnClickListener { onSongClick(song) }
        holder.favoriteBtn.setOnClickListener { onFavoriteClick(song) }
    }

    override fun getItemCount(): Int = songs.size
}
