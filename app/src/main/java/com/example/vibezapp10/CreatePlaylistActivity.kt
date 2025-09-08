package com.example.vibezapp10

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CreatePlaylistActivity : AppCompatActivity() {

    private lateinit var playlistNameInput: EditText
    private lateinit var songsRecycler: RecyclerView
    private lateinit var btnCreate: Button

    private val songsAdapter = SongsAdapter() // Placeholder adapter for selecting songs

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_playlist)

        playlistNameInput = findViewById(R.id.playlist_name_input)
        songsRecycler = findViewById(R.id.songs_recycler)
        btnCreate = findViewById(R.id.btn_create_playlist)

        setupRecyclerView()
        loadSongs()

        btnCreate.setOnClickListener {
            createPlaylist()
        }

        // Toolbar back navigation
        findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar).setNavigationOnClickListener {
            finish()
        }
    }

    private fun setupRecyclerView() {
        songsRecycler.layoutManager = LinearLayoutManager(this)
        songsRecycler.adapter = songsAdapter
        songsAdapter.setSelectionMode(true) // Allow selecting multiple songs
    }

    private fun loadSongs() {
        // TODO: Fetch songs from Firebase or API
        val dummySongs = listOf(
            Song("Water", "Tyla"),
            Song("Sunset", "John Doe"),
            Song("Night Drive", "Synthwave Artist")
        )
        songsAdapter.setData(dummySongs)
    }

    private fun createPlaylist() {
        val name = playlistNameInput.text.toString().trim()
        val selectedSongs = songsAdapter.getSelectedSongs()

        if (name.isEmpty()) {
            Toast.makeText(this, "Enter playlist name", Toast.LENGTH_SHORT).show()
            return
        }

        if (selectedSongs.isEmpty()) {
            Toast.makeText(this, "Select at least one song", Toast.LENGTH_SHORT).show()
            return
        }

        // TODO: Save playlist to Firebase or API
        val newPlaylist = PlaylistDetail(name, selectedSongs)
        Toast.makeText(this, "Playlist '${newPlaylist.name}' created!", Toast.LENGTH_SHORT).show()
        finish()
    }
}

// --- Song Model ---
data class Song(
    val title: String,
    val artist: String
)

// --- PlaylistDetail Model ---
data class PlaylistDetail(
    val name: String,
    val songs: List<Song>
)

// --- SongsAdapter Placeholder ---
class SongsAdapter : RecyclerView.Adapter<SongsAdapter.SongViewHolder>() {

    private var data: List<Song> = emptyList()
    private var selectedSongs: MutableSet<Song> = mutableSetOf()
    private var selectionMode = false

    fun setData(songs: List<Song>) {
        data = songs
        notifyDataSetChanged()
    }

    fun setSelectionMode(enabled: Boolean) {
        selectionMode = enabled
    }

    fun getSelectedSongs(): List<Song> = selectedSongs.toList()

    override fun onCreateViewHolder(parent: android.view.ViewGroup, viewType: Int): SongViewHolder {
        val view = android.view.LayoutInflater.from(parent.context)
            .inflate(android.R.layout.simple_list_item_multiple_choice, parent, false)
        return SongViewHolder(view)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        val song = data[position]
        holder.bind(song)
    }

    inner class SongViewHolder(itemView: android.view.View) : RecyclerView.ViewHolder(itemView) {
        private val checkBox = itemView.findViewById<android.widget.CheckedTextView>(android.R.id.text1)

        fun bind(song: Song) {
            checkBox.text = "${song.title} - ${song.artist}"
            checkBox.isChecked = selectedSongs.contains(song)

            itemView.setOnClickListener {
                if (!selectionMode) return@setOnClickListener

                if (selectedSongs.contains(song)) {
                    selectedSongs.remove(song)
                    checkBox.isChecked = false
                } else {
                    selectedSongs.add(song)
                    checkBox.isChecked = true
                }
            }
        }
    }
}
