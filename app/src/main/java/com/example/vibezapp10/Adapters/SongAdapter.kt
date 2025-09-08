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
import com.bumptech.glide.Glide

class SongAdapter(
    private val songs: MutableList<Song>,
    private val onSongClick: (Song) -> Unit,
    private val onFavoriteClick: ((Song) -> Unit)? = null // Optional
) : RecyclerView.Adapter<SongAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleText: TextView = view.findViewById(R.id.song_title)
        val artistText: TextView = view.findViewById(R.id.song_artist)
        val durationText: TextView = view.findViewById(R.id.song_duration)
        val albumImage: ImageView = view.findViewById(R.id.album_image)
        val favoriteBtn: ImageButton? = view.findViewById(R.id.btn_favorite)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_song, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val song = songs[position]

        holder.titleText.text = song.title
        holder.artistText.text = song.artist
        holder.durationText.text = formatDuration(song.duration)

        // Load album image using Glide
        Glide.with(holder.itemView.context)
            .load(song.imageUrl)
            .placeholder(R.drawable.placeholder_album)
            .into(holder.albumImage)

        // Handle favorite button if exists
        holder.favoriteBtn?.setImageResource(
            if (song.isFavorite) R.drawable.ic_favorite_filled
            else R.drawable.ic_favorite_outline
        )
        holder.favoriteBtn?.setOnClickListener { onFavoriteClick?.invoke(song) }

        holder.itemView.setOnClickListener { onSongClick(song) }
    }

    override fun getItemCount() = songs.size

    fun updateSongs(newSongs: List<Song>) {
        songs.clear()
        songs.addAll(newSongs)
        notifyDataSetChanged()
    }

    private fun formatDuration(duration: Long): String {
        val minutes = duration / 60
        val seconds = duration % 60
        return String.format("%d:%02d", minutes, seconds)
    }
}
