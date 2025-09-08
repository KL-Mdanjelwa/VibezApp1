package com.example.vibezapp10.api

import com.example.vibezapp10.Models.*

object ApiPlaceholder {

    private val demoSongs = listOf(
        Song("s1", "Water", "Tyla", coverUrl = null),
        Song("s2", "Getting Late", "Tyla", coverUrl = null),
        Song("s3", "Truth or Dare", "Tyla", coverUrl = null),
        Song("s4", "To Last", "Tyla", coverUrl = null),
    )

    private val demoAlbums = listOf(
        Album("a1", "TYLA", coverUrl = null),
        Album("a2", "Singles", coverUrl = null),
    )

    private var following = mutableSetOf<String>()

    fun getArtist(artistId: String): Artist? =
        Artist(
            id = artistId.ifBlank { "artist_tyla" },
            name = "Tyla",
            imageUrl = null,
            followers = 1_234_567,
            bio = "South African singer and songwriter known for ‘Water’."
        )

    fun getArtistPopularSongs(artistId: String): List<Song> = demoSongs

    fun getArtistAlbums(artistId: String): List<Album> = demoAlbums

    fun isFollowingArtist(artistId: String): Boolean = following.contains(artistId.ifBlank { "artist_tyla" })

    fun setFollowArtist(artistId: String, follow: Boolean) {
        val id = artistId.ifBlank { "artist_tyla" }
        if (follow) following.add(id) else following.remove(id)
        // TODO: Replace with Firebase write to /users/{uid}/following/{artistId}
    }

    fun shufflePick(list: List<Song>): Song? = list.shuffled().firstOrNull()
}