package com.example.vibezapp10.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.vibezapp10.Models.DownloadedSong
import com.example.vibezapp10.R

class DownloadsAdapter(
    private val downloads: List<DownloadedSong>,
    private val onItemClick: (DownloadedSong) -> Unit
) : RecyclerView.Adapter<DownloadsAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleText: TextView = view.findViewById(R.id.download_title)
        val artistText: TextView = view.findViewById(R.id.download_artist)
        val albumImage: ImageView = view.findViewById(R.id.download_album)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_download, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val song = downloads[position]
        holder.titleText.text = song.title
        holder.artistText.text = song.artist
        holder.albumImage.setImageResource(song.albumImage)

        holder.itemView.setOnClickListener { onItemClick(song) }
    }

    override fun getItemCount() = downloads.size
}
