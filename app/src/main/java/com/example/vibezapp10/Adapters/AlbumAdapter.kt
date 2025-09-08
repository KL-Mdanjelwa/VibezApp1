package com.example.vibezapp10.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.vibezapp10.R
import com.example.vibezapp10.Models.Album

class AlbumAdapter(
    private var items: List<Album>,
    private val onClick: (Album) -> Unit
) : RecyclerView.Adapter<AlbumAdapter.VH>() {

    inner class VH(v: View) : RecyclerView.ViewHolder(v) {
        private val cover: ImageView = v.findViewById(R.id.item_album_cover)
        private val title: TextView = v.findViewById(R.id.item_album_title)
        fun bind(album: Album) {
            title.text = album.title
            Glide.with(itemView).load(album.coverUrl ?: R.drawable.placeholder_album).into(cover)
            itemView.setOnClickListener { onClick(album) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_album_square, parent, false)
        return VH(v)
    }
    override fun onBindViewHolder(holder: VH, position: Int) = holder.bind(items[position])
    override fun getItemCount() = items.size
    fun submit(newItems: List<Album>) { items = newItems; notifyDataSetChanged() }
}