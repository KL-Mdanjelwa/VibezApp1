package com.example.vibezapp10.Models

data class Playlist(
    val id: String,
    val name: String,
    val description: String,
    val imageUrl: String,
    val songs: List<Song>,
    val createdAt: Long
)