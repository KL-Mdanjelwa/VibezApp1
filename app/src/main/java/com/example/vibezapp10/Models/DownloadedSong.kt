package com.example.vibezapp10.Models

data class DownloadedSong(
    val id: String,
    val title: String,
    val artist: String,
    val filePath: String,    // local path to simulate offline file
    val albumImage: Int      // drawable resource for album art placeholder
)
