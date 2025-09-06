package com.example.vibezapp10

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView

class DownloadsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_downloads)

        val recyclerView = findViewById<RecyclerView>(R.id.downloads_recycler)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Setup downloads list
    }
}