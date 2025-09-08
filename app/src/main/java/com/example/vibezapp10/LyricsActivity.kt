package com.example.vibezapp10.Activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.vibezapp10.R

class LyricsActivity : AppCompatActivity() {

    private lateinit var toolbar: Toolbar
    private lateinit var songTitle: TextView
    private lateinit var artistName: TextView
    private lateinit var albumArt: ImageView
    private lateinit var btnShare: ImageButton

    private lateinit var lyricsText: TextView
    private lateinit var lyricsScroll: View
    private lateinit var loadingSection: View
    private lateinit var errorSection: View
    private lateinit var btnRetry: Button

    private var songId: String? = null // optional if you fetch by song ID

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lyrics)

        // Initialize views
        toolbar = findViewById(R.id.toolbar)
        songTitle = findViewById(R.id.song_title)
        artistName = findViewById(R.id.artist_name)
        albumArt = findViewById(R.id.song_album_art)
        btnShare = findViewById(R.id.btn_share_lyrics)

        lyricsText = findViewById(R.id.lyrics_text)
        lyricsScroll = findViewById(R.id.lyrics_scroll)
        loadingSection = findViewById(R.id.lyrics_loading)
        errorSection = findViewById(R.id.lyrics_error)
        btnRetry = findViewById(R.id.btn_retry_lyrics)

        // Toolbar back navigation
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener { onBackPressed() }

        // Retrieve song info from intent
        songId = intent.getStringExtra("song_id")
        songTitle.text = intent.getStringExtra("song_title") ?: "Unknown"
        artistName.text = intent.getStringExtra("artist_name") ?: "Unknown"

        // Load album image if provided
        val albumResId = intent.getIntExtra("album_art_res", R.drawable.placeholder_album)
        albumArt.setImageResource(albumResId)

        btnShare.setOnClickListener { shareLyrics() }
        btnRetry.setOnClickListener { loadLyrics() }

        // Initial load
        loadLyrics()
    }

    private fun loadLyrics() {
        showLoading(true)
        showError(false)

        // Simulate API fetch (replace with real API)
        LyricsApi.fetchLyrics(songId ?: "") { lyrics, error ->
            showLoading(false)
            if (lyrics != null) {
                lyricsText.text = lyrics
                lyricsScroll.visibility = View.VISIBLE
                showError(false)
            } else {
                showError(true)
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        loadingSection.visibility = if (isLoading) View.VISIBLE else View.GONE
        lyricsScroll.visibility = if (isLoading) View.GONE else lyricsScroll.visibility
    }

    private fun showError(isError: Boolean) {
        errorSection.visibility = if (isError) View.VISIBLE else View.GONE
        lyricsScroll.visibility = if (isError) View.GONE else lyricsScroll.visibility
    }

    private fun shareLyrics() {
        val lyrics = lyricsText.text.toString()
        if (lyrics.isNotEmpty()) {
            val intent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT, "${songTitle.text} - ${artistName.text}\n\n$lyrics")
            }
            startActivity(Intent.createChooser(intent, "Share lyrics via"))
        }
    }
}
