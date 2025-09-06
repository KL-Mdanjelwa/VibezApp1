package com.example.vibezapp10

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class PlaylistsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_playlists)

        val recyclerView = findViewById<RecyclerView>(R.id.playlists_recycler)
        val fab = findViewById<FloatingActionButton>(R.id.fab_create_playlist)

        fab.setOnClickListener {
            startActivity(Intent(this, CreatePlaylistActivity::class.java))
        }

        // Setup recycler view
        recyclerView.layoutManager = LinearLayoutManager(this)
        // Set adapter
    }
}