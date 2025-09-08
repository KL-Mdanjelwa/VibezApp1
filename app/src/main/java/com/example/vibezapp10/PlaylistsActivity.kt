package com.example.vibezapp10

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout

class PlaylistsActivity : AppCompatActivity() {

    private lateinit var playlistsRecycler: RecyclerView
    private lateinit var emptyState: View
    private lateinit var totalPlaylists: TextView
    private lateinit var totalSongs: TextView
    private lateinit var playlistTabs: TabLayout

    private val playlistsAdapter = PlaylistsAdapter() // Placeholder adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_playlists)

        playlistsRecycler = findViewById(R.id.playlists_recycler)
        emptyState = findViewById(R.id.empty_state)
        totalPlaylists = findViewById(R.id.total_playlists)
        totalSongs = findViewById(R.id.total_songs)
        playlistTabs = findViewById(R.id.playlist_tabs)
        val fab = findViewById<FloatingActionButton>(R.id.fab_create_playlist)

        setupRecyclerView()
        setupTabs()
        loadPlaylists()

        fab.setOnClickListener {
            startActivity(Intent(this, CreatePlaylistActivity::class.java))
        }

        // Optional: Toolbar back navigation
        findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar).setNavigationOnClickListener {
            finish()
        }
    }

    private fun setupRecyclerView() {
        playlistsRecycler.layoutManager = LinearLayoutManager(this)
        playlistsRecycler.adapter = playlistsAdapter
    }

    private fun setupTabs() {
        // Example tabs: All / Favorites / Recently Added
        val tabs = listOf("All", "Favorites", "Recently Added")
        tabs.forEach { tab ->
            playlistTabs.addTab(playlistTabs.newTab().setText(tab))
        }

        playlistTabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                // TODO: Filter playlists based on tab
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
    }

    private fun loadPlaylists() {
        // TODO: Fetch playlists from Firebase or your API
        val dummyPlaylists = listOf(
            Playlist("Chill Vibes", 12),
            Playlist("Workout Hits", 20),
            Playlist("Party Time", 15)
        )

        if (dummyPlaylists.isEmpty()) {
            playlistsRecycler.visibility = View.GONE
            emptyState.visibility = View.VISIBLE
        } else {
            playlistsRecycler.visibility = View.VISIBLE
            emptyState.visibility = View.GONE
            playlistsAdapter.setData(dummyPlaylists)
            totalPlaylists.text = dummyPlaylists.size.toString()
            totalSongs.text = dummyPlaylists.sumOf { it.songCount }.toString()
        }
    }
}

// --- Playlist data class ---
data class Playlist(
    val name: String,
    val songCount: Int
)

// --- Adapter placeholder ---
class PlaylistsAdapter : RecyclerView.Adapter<PlaylistsAdapter.PlaylistViewHolder>() {

    private var data: List<Playlist> = emptyList()

    fun setData(playlists: List<Playlist>) {
        data = playlists
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: android.view.ViewGroup, viewType: Int): PlaylistViewHolder {
        val view = android.view.LayoutInflater.from(parent.context)
            .inflate(android.R.layout.simple_list_item_2, parent, false)
        return PlaylistViewHolder(view)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: PlaylistViewHolder, position: Int) {
        val playlist = data[position]
        holder.bind(playlist)
    }

    inner class PlaylistViewHolder(itemView: android.view.View) : RecyclerView.ViewHolder(itemView) {
        private val title: TextView = itemView.findViewById(android.R.id.text1)
        private val subtitle: TextView = itemView.findViewById(android.R.id.text2)

        fun bind(playlist: Playlist) {
            title.text = playlist.name
            subtitle.text = "${playlist.songCount} songs"

            itemView.setOnClickListener {
                // TODO: Open playlist detail
            }
        }
    }
}
