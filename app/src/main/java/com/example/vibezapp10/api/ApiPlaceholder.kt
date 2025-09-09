package com.example.vibezapp10.api

import com.example.vibezapp10.Models.*

object ApiPlaceholder {

    val demoSongs = listOf(
        Song(
            id = "s1",
            title = "Water",
            artist = "Tyla",
            album = "Album 1",
            duration = 180_000L, // duration in milliseconds (3 minutes)
            coverUrl = null,
            audioUrl = "https://example.com/audio/water.mp3"
        ),
        Song(
            id = "s2",
            title = "Getting Late",
            artist = "Tyla",
            album = "Album 2",
            duration = 200_000L,
            coverUrl = null,
            audioUrl = "https://example.com/audio/getting_late.mp3"
        ),
        Song(
            id = "s3",
            title = "Truth or Dare",
            artist = "Tyla",
            album = "Album 3",
            duration = 210_000L,
            coverUrl = null,
            audioUrl = "https://example.com/audio/truth_or_dare.mp3"
        ),
        Song(
            id = "s4",
            title = "To Last",
            artist = "Tyla",
            album = "Album 4",
            duration = 190_000L,
            coverUrl = null,
            audioUrl = "https://example.com/audio/to_last.mp3"
        )
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

    fun searchSongs(query: String): List<Song> {
        return demoSongs.filter { song ->
            song.title.contains(query, ignoreCase = true) ||
                    song.artist.contains(query, ignoreCase = true) ||
                    song.album.contains(query, ignoreCase = true)
        }
    }

    fun getArtistAlbums(artistId: String): List<Album> = demoAlbums

    fun isFollowingArtist(artistId: String): Boolean = following.contains(artistId.ifBlank { "artist_tyla" })

    fun setFollowArtist(artistId: String, follow: Boolean) {
        val id = artistId.ifBlank { "artist_tyla" }
        if (follow) following.add(id) else following.remove(id)
        // TODO: Replace with Firebase write to /users/{uid}/following/{artistId}
    }

    fun shufflePick(list: List<Song>): Song? = list.shuffled().firstOrNull()
}