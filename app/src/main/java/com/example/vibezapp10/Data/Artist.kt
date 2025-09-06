package com.example.vibezapp10.Data

data class Artist(
    val id: String,
    val name: String,
    val imageUrl: String,
    val bio: String,
    val genres: List<String>,
    val followers: Int
)