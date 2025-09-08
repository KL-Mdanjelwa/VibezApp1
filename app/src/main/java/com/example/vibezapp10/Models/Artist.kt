package com.example.vibezapp10.Models

data class Artist(
    val id: String,
    val name: String,
    val imageUrl: String? = null,
    val followers: Long = 0,
    val bio: String? = null
)