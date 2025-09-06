package com.example.vibezapp10

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LyricsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lyrics)

        val lyricsTextView = findViewById<TextView>(R.id.lyrics_text)
        val scrollView = findViewById<ScrollView>(R.id.lyrics_scroll)

        // Load and display lyrics
    }
}