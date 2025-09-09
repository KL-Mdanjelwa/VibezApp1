package com.example.vibezapp10

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.vibezapp10.Adapters.AlbumAdapter
import com.example.vibezapp10.Adapters.SongAdapterLite
import com.example.vibezapp10.api.ApiPlaceholder
import com.example.vibezapp10.Models.Album
import com.example.vibezapp10.Models.Artist
import com.example.vibezapp10.Models.Song

class ArtistProfileActivity : AppCompatActivity() {

    private lateinit var followBtn: Button
    private lateinit var shufflePlayBtn: ImageButton
    private lateinit var artistImage: ImageView
    private lateinit var artistName: TextView
    private lateinit var artistFollowers: TextView
    private lateinit var artistBio: TextView
    private lateinit var songsRecycler: RecyclerView
    private lateinit var albumsRecycler: RecyclerView

    private var artistId: String = ""
    private var following: Boolean = false
    private var songs: List<Song> = emptyList()
    private var albums: List<Album> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_artist_profile)

        // Toolbar
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }

        // Views
        followBtn = findViewById(R.id.btn_follow)
        shufflePlayBtn = findViewById(R.id.btn_shuffle_play)
        artistImage = findViewById(R.id.artist_image)
        artistName = findViewById(R.id.artist_name)
        artistFollowers = findViewById(R.id.artist_followers)
        artistBio = findViewById(R.id.artist_bio)
        songsRecycler = findViewById(R.id.artist_songs_recycler)
        albumsRecycler = findViewById(R.id.artist_albums_recycler)

        // Intent extras (fallbacks provided)
        artistId = intent.getStringExtra("artist_id") ?: ""
        val initialName = intent.getStringExtra("artist_name")
        val initialImage = intent.getStringExtra("artist_image")

        // Bind initial header (instant UI) while placeholders load
        initialName?.let { artistName.text = it }
        Glide.with(this).load(initialImage ?: R.drawable.placeholder_artist).into(artistImage)

        // Setup recyclers
        songsRecycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        albumsRecycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        loadArtistData()

        followBtn.setOnClickListener {
            following = !following
            updateFollowUi()
            // TODO: Replace with Firebase call
            ApiPlaceholder.setFollowArtist(artistId, following)
        }

        shufflePlayBtn.setOnClickListener {
            // Shuffle play from popular songs (placeholder)
            val start = ApiPlaceholder.shufflePick(songs)
            if (start != null) {
                val i = Intent(this, MusicPlayerActivity::class.java).apply {
                    putExtra("song_id", start.id)
                    putExtra("song_title", start.title)
                    putExtra("song_artist", start.artist)
                    putExtra("song_cover", start.coverUrl)
                }
                startActivity(i)
            }
        }
    }

    private fun loadArtistData() {
        // TODO: Replace these with real async Firebase calls
        val artist: Artist? = ApiPlaceholder.getArtist(artistId)
        songs = ApiPlaceholder.getArtistPopularSongs(artistId)
        albums = ApiPlaceholder.getArtistAlbums(artistId)
        following = ApiPlaceholder.isFollowingArtist(artistId)

        // Header
        artist?.let {
            artistName.text = it.name
            artistBio.text = it.bio ?: "No bio available."
            artistFollowers.text = formatFollowers(it.followers)
            Glide.with(this).load(it.imageUrl ?: R.drawable.placeholder_artist).into(artistImage)
        }

        // Lists
        val songAdapter = SongAdapterLite(songs) { song ->
            val i = Intent(this, MusicPlayerActivity::class.java).apply {
                putExtra("song_id", song.id)
                putExtra("song_title", song.title)
                putExtra("song_artist", song.artist)
                putExtra("song_cover", song.coverUrl)
            }
            startActivity(i)
        }
        songsRecycler.adapter = songAdapter

        val albumAdapter = AlbumAdapter(albums) { album ->
            // TODO: Create AlbumDetailActivity (placeholder navigation)
            val i = Intent(this, PlaylistsActivity::class.java).apply {
                putExtra("album_id", album.id)
                putExtra("album_title", album.title)
                putExtra("album_cover", album.coverUrl)
                putExtra("artist_id", artistId)
            }
            startActivity(i)
        }
        albumsRecycler.adapter = albumAdapter

        updateFollowUi()
    }

    private fun updateFollowUi() {
        followBtn.text = if (following) "Following" else "Follow"
        followBtn.isSelected = following
    }

    private fun formatFollowers(count: Long): String {
        return when {
            count >= 1_000_000 -> String.format("%.1fM followers", count / 1_000_000f)
            count >= 1_000 -> String.format("%.1fK followers", count / 1_000f)
            else -> "$count followers"
        }
    }
}
