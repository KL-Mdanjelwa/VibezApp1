package com.example.vibezapp10

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vibezapp10.Adapters.DownloadsAdapter
import com.example.vibezapp10.Models.DownloadedSong

class DownloadsActivity : AppCompatActivity() {

    private val downloads = mutableListOf<DownloadedSong>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_downloads)

        val recyclerView = findViewById<RecyclerView>(R.id.downloads_recycler)
        val emptyState = findViewById<View>(R.id.empty_state)

        recyclerView.layoutManager = LinearLayoutManager(this)

        // Placeholder offline downloads
        downloads.addAll(listOf(
            DownloadedSong("1", "Water", "Tyla", "/storage/emulated/0/Music/water.mp3", R.drawable.placeholder_album),
            DownloadedSong("2", "Sunshine", "Artist 2", "/storage/emulated/0/Music/sunshine.mp3", R.drawable.placeholder_album),
            DownloadedSong("3", "Moonlight", "Artist 3", "/storage/emulated/0/Music/moonlight.mp3", R.drawable.placeholder_album)
        ))

        val adapter = DownloadsAdapter(downloads) { song ->
            // TODO: Play song offline using MediaPlayer or ExoPlayer
        }
        recyclerView.adapter = adapter

        // Show empty state if no downloads
        emptyState.visibility = if (downloads.isEmpty()) View.VISIBLE else View.GONE
    }
}
