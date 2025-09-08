package com.example.vibezapp10

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.vibezapp10.Models.User

class EditProfileActivity : AppCompatActivity() {

    private lateinit var profileImageView: ImageView
    private lateinit var nameEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var genresEditText: EditText
    private lateinit var btnSave: Button

    // Placeholder for user object
    private var currentUser: User? = null

    // Image picker result
    private val pickImageLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let {
            profileImageView.setImageURI(uri)
            // TODO: Upload image to server and get URL
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        profileImageView = findViewById(R.id.profile_image)
        nameEditText = findViewById(R.id.edit_name)
        emailEditText = findViewById(R.id.edit_email)
        genresEditText = findViewById(R.id.edit_genres)
        btnSave = findViewById(R.id.btn_save_profile)

        loadUserData()

        profileImageView.setOnClickListener {
            pickImageLauncher.launch("image/*")
        }

        btnSave.setOnClickListener {
            saveProfile()
        }
    }

    private fun loadUserData() {
        // TODO: Fetch user from API / local storage
        // Using placeholder data
        currentUser = User(
            id = "123",
            name = "Davida Oyowa",
            email = "davida@example.com",
            profileImageUrl = "",
            favoriteGenres = listOf("Hip Hop", "Afrobeat")
        )

        currentUser?.let {
            nameEditText.setText(it.name)
            emailEditText.setText(it.email)
            genresEditText.setText(it.favoriteGenres.joinToString(", "))
            // TODO: Load image via Glide/Picasso if URL exists
        }
    }

    private fun saveProfile() {
        val updatedName = nameEditText.text.toString().trim()
        val updatedEmail = emailEditText.text.toString().trim()
        val updatedGenres = genresEditText.text.toString()
            .split(",")
            .map { genre -> genre.trim() }
            .filter { it.isNotEmpty() }

        // TODO: API call to save user changes
        Toast.makeText(this, "Profile updated", Toast.LENGTH_SHORT).show()

        // Close activity after save
        setResult(Activity.RESULT_OK)
        finish()
    }
}
