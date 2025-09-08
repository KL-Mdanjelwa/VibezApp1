package com.example.vibezapp10.Adapters



import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.vibezapp10.R
import com.example.vibezapp10.Models.Song

class SongAdapterLite(
    private var items: List<Song>,
    private val onClick: (Song) -> Unit
) : RecyclerView.Adapter<SongAdapterLite.VH>() {

    inner class VH(v: View) : RecyclerView.ViewHolder(v) {
        private val cover: ImageView = v.findViewById(R.id.item_song_cover)
        private val title: TextView = v.findViewById(R.id.item_song_title)
        private val subtitle: TextView = v.findViewById(R.id.item_song_subtitle)
        fun bind(song: Song) {
            title.text = song.title
            subtitle.text = song.artist
            Glide.with(itemView).load(song.coverUrl ?: R.drawable.ic_music_note).into(cover)
            itemView.setOnClickListener { onClick(song) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_song_row, parent, false)
        return VH(v)
    }
    override fun onBindViewHolder(holder: VH, position: Int) = holder.bind(items[position])
    override fun getItemCount() = items.size
    fun submit(newItems: List<Song>) { items = newItems; notifyDataSetChanged() }
}
