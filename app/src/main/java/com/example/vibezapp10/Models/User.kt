package com.example.vibezapp10.Models

data class User(
    val id: String,
    val name: String,
    val email: String,
    val profileImageUrl: String,
    val favoriteGenres: List<String>
)