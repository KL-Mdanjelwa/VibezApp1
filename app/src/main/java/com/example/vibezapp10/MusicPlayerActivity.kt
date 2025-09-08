package com.example.vibezapp10

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MusicPlayerActivity : AppCompatActivity() {

    private var isPlaying = false
    private var isShuffle = false
    private var isRepeat = false
    private var isFavorite = false

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
        val lyricsBtn = findViewById<Button>(R.id.btn_show_lyrics)

        // --- Play/Pause ---
        playPauseBtn.setOnClickListener {
            isPlaying = !isPlaying
            playPauseBtn.setImageResource(
                if (isPlaying) R.drawable.ic_pause else R.drawable.ic_play
            )
            Toast.makeText(this, if (isPlaying) "Playing" else "Paused", Toast.LENGTH_SHORT).show()
            // TODO: Hook up ExoPlayer play/pause here
        }

        // --- Next Track ---
        nextBtn.setOnClickListener {
            Toast.makeText(this, "Next Track", Toast.LENGTH_SHORT).show()
            // TODO: Skip to next track
        }

        // --- Previous Track ---
        prevBtn.setOnClickListener {
            Toast.makeText(this, "Previous Track", Toast.LENGTH_SHORT).show()
            // TODO: Skip to previous track
        }

        // --- Shuffle Toggle ---
        shuffleBtn.setOnClickListener {
            isShuffle = !isShuffle
            shuffleBtn.setColorFilter(
                if (isShuffle) getColor(R.color.red_main) else getColor(R.color.white)
            )
            Toast.makeText(this, if (isShuffle) "Shuffle On" else "Shuffle Off", Toast.LENGTH_SHORT).show()
        }

        // --- Repeat Toggle ---
        repeatBtn.setOnClickListener {
            isRepeat = !isRepeat
            repeatBtn.setColorFilter(
                if (isRepeat) getColor(R.color.red_main) else getColor(R.color.white)
            )
            Toast.makeText(this, if (isRepeat) "Repeat On" else "Repeat Off", Toast.LENGTH_SHORT).show()
        }

        // --- Favorite Toggle ---
        favoriteBtn.setOnClickListener {
            isFavorite = !isFavorite
            favoriteBtn.setImageResource(
                if (isFavorite) R.drawable.ic_favorite_filled else R.drawable.ic_favorite_border
            )
            Toast.makeText(this, if (isFavorite) "Added to Favorites" else "Removed from Favorites", Toast.LENGTH_SHORT).show()
            // TODO: Save favorite to Firebase
        }

        // --- SeekBar ---
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    // TODO: Seek player to progress
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        // --- Show Lyrics ---
        lyricsBtn.setOnClickListener {
            startActivity(Intent(this, LyricsActivity::class.java))
        }
    }
}
