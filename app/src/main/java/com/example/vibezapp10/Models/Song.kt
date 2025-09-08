package com.example.vibezapp10.Models

data class Song(
    val id: String,
    val title: String,
    val artist: String,
    val album: String,
    val duration: Long,
    val coverUrl: String? = null,
    val audioUrl: String,
    val localPath: String? = null,   // Path for offline storage,
    var isFavorite: Boolean = false,
    var isDownloaded: Boolean = false
)