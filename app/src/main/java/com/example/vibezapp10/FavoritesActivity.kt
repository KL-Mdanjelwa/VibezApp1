package com.example.vibezapp10

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vibezapp10.Adapters.FavoritesAdapter

class FavoritesActivity : AppCompatActivity() {

    private val favoriteSongs = mutableListOf<Song>()
    private lateinit var adapter: FavoritesAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var emptyState: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorites)

        recyclerView = findViewById(R.id.favorites_recycler)
        emptyState = findViewById(R.id.empty_state)

        adapter = FavoritesAdapter(favoriteSongs,
            onSongClick = { song ->
                // Play song or open player
            },
            onFavoriteClick = { song ->
                // Remove from favorites
                favoriteSongs.remove(song)
                adapter.notifyDataSetChanged()
                toggleEmptyState()
            })

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        loadFavorites()
    }

    private fun loadFavorites() {
        // Placeholder: Add dummy favorites
        favoriteSongs.addAll(listOf(
            Song("1", "Water", "Tyla", "placeholder_album"),
            Song("2", "Shape of You", "Ed Sheeran", "placeholder_album", localPath = "/offline/song2.mp3")
        ))
        adapter.notifyDataSetChanged()
        toggleEmptyState()
    }

    private fun toggleEmptyState() {
        emptyState.visibility = if (favoriteSongs.isEmpty()) View.VISIBLE else View.GONE
        recyclerView.visibility = if (favoriteSongs.isEmpty()) View.GONE else View.VISIBLE
    }
}
