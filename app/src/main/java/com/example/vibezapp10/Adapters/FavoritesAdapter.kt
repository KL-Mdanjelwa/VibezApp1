package com.example.vibezapp10.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.vibezapp10.Models.Song
import com.example.vibezapp10.R

class FavoritesAdapter(
    private val songs: List<Song>,
    private val onSongClick: (Song) -> Unit,
    private val onFavoriteClick: (Song) -> Unit
) : RecyclerView.Adapter<FavoritesAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val coverImage: ImageView = view.findViewById(R.id.song_cover)
        val titleText: TextView = view.findViewById(R.id.song_title)
        val artistText: TextView = view.findViewById(R.id.song_artist)
        val favoriteButton: ImageView = view.findViewById(R.id.song_favorite)
        val offlineIndicator: ImageView = view.findViewById(R.id.song_offline)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_favorite_song, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val song = songs[position]

        holder.titleText.text = song.title
        holder.artistText.text = song.artist

        // Cover image
        Glide.with(holder.coverImage.context)
            .load(song.coverUrl ?: R.drawable.ic_library_music)
            .placeholder(R.drawable.ic_library_music)
            .into(holder.coverImage)

        // Favorite icon
        holder.favoriteButton.setImageResource(
            if (song.isFavorite) R.drawable.ic_favorite_filled else R.drawable.ic_favorite_outline
        )

        // Offline/download indicator
        holder.offlineIndicator.visibility = if (song.isDownloaded || song.localPath != null) View.VISIBLE else View.GONE

        // Click listeners
        holder.itemView.setOnClickListener { onSongClick(song) }
        holder.favoriteButton.setOnClickListener { onFavoriteClick(song) }
    }

    override fun getItemCount() = songs.size
}
