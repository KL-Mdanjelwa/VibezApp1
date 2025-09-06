package com.example.vibezapp10

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MusicPlayerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_music_player)

        val playPauseBtn = findViewById<ImageButton>(R.id.btn_play_pause)
        val nextBtn = findViewById<ImageButton>(R.id.btn_next)
        val prevBtn = findViewById<ImageButton>(R.id.btn_previous)
        val shuffleBtn = findViewById<ImageButton>(R.id.btn_shuffle)
        val repeatBtn = findViewById<ImageButton>(R.id.btn_repeat)
        val favoriteBtn = findViewById<ImageButton>(R.id.btn_favorite)
        val seekBar = findViewById<SeekBar>(R.id.seek_bar)

        playPauseBtn.setOnClickListener {
            // Handle play/pause
        }

        nextBtn.setOnClickListener {
            // Handle next track
        }

        prevBtn.setOnClickListener {
            // Handle previous track
        }

        favoriteBtn.setOnClickListener {
            // Handle favorite toggle
        }

        findViewById<Button>(R.id.btn_show_lyrics).setOnClickListener {
            startActivity(Intent(this, LyricsActivity::class.java))
        }
    }
}