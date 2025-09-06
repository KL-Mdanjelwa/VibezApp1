package com.example.vibezapp10

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ArtistProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_artist_profile)

        val followBtn = findViewById<Button>(R.id.btn_follow)
        val songsRecycler = findViewById<RecyclerView>(R.id.artist_songs_recycler)
        val albumsRecycler = findViewById<RecyclerView>(R.id.artist_albums_recycler)

        followBtn.setOnClickListener {
            // Handle follow/unfollow
        }
    }
}