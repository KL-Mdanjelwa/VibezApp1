package com.example.vibezapp10.Data

data class Song(
    val id: String,
    val title: String,
    val artist: String,
    val album: String,
    val duration: Long,
    val imageUrl: String,
    val audioUrl: String,
    var isFavorite: Boolean = false,
    var isDownloaded: Boolean = false
)