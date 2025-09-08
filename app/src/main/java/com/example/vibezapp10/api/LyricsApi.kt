package com.example.vibezapp10.api

import android.os.Handler
import android.os.Looper

object LyricsApi {

    /**
     * Simulate fetching lyrics for a song.
     * @param songId The ID of the song
     * @param callback Returns lyrics string if successful, or null if failed
     */
    fun fetchLyrics(songId: String, callback: (lyrics: String?, error: Boolean) -> Unit) {
        // Simulate network delay (2 seconds)
        Handler(Looper.getMainLooper()).postDelayed({
            if (songId.isNotEmpty()) {
                // Example lyrics for testing
                val lyrics = """
                    Water, water, everywhere
                    Flowing through the night
                    Music in the air
                    Everything feels right
                    
                    Chorus:
                    Oh, the rhythm takes me higher
                    Feel the beat, ignite the fire
                    Sing along, never tire
                    Water, music, my desire
                """.trimIndent()
                callback(lyrics, false)
            } else {
                // Simulate error
                callback(null, true)
            }
        }, 2000)
    }
}