package com.example.vibezapp10.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.vibezapp10.Models.Playlist
import com.example.vibezapp10.R

class PlaylistAdapter(
    private val playlists: List<Playlist>,
    private val onPlaylistClick: (Playlist) -> Unit
) : RecyclerView.Adapter<PlaylistAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameText: TextView = view.findViewById(R.id.playlist_name)
        val descriptionText: TextView = view.findViewById(R.id.playlist_description)
        val songsCountText: TextView = view.findViewById(R.id.songs_count)
        val playlistImage: ImageView = view.findViewById(R.id.playlist_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_playlist, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val playlist = playlists[position]
        holder.nameText.text = playlist.name
        holder.descriptionText.text = playlist.description
        holder.songsCountText.text = "${playlist.songs.size} songs"

        holder.itemView.setOnClickListener { onPlaylistClick(playlist) }
    }

    override fun getItemCount() = playlists.size
}