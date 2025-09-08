package com.example.vibezapp10.Fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vibezapp10.Models.RecentActivity
import com.example.vibezapp10.Models.RecentActivityAdapter
import com.example.vibezapp10.PlaylistsActivity
import com.example.vibezapp10.R

class HomeFragment : Fragment() {

    private lateinit var recentActivitiesRecycler: RecyclerView
    private lateinit var recentActivitiesAdapter: RecentActivityAdapter
    private val recentActivities = mutableListOf<RecentActivity>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val playlistsBtn = view.findViewById<Button>(R.id.btn_playlists)
        val shareBtn = view.findViewById<Button>(R.id.btn_share)

        playlistsBtn.setOnClickListener {
            startActivity(Intent(requireContext(), PlaylistsActivity::class.java))
        }

        shareBtn.setOnClickListener {
            // TODO: Implement share logic
        }

        setupRecyclerView(view)
        fetchRecentActivities()
    }

    private fun setupRecyclerView(view: View) {
        recentActivitiesRecycler = view.findViewById(R.id.recent_activities_recycler)
        recentActivitiesAdapter = RecentActivityAdapter(recentActivities) { activity ->
            // TODO: Handle item click
        }
        recentActivitiesRecycler.layoutManager = LinearLayoutManager(requireContext())
        recentActivitiesRecycler.adapter = recentActivitiesAdapter
    }

    private fun fetchRecentActivities() {
        // TODO: Replace with API call
        val mockData = listOf(
            RecentActivity("Downloaded 'Song A'", "Today"),
            RecentActivity("Liked 'Song B'", "Yesterday"),
            RecentActivity("Played 'Song C'", "2 days ago")
        )
        recentActivities.clear()
        recentActivities.addAll(mockData)
        recentActivitiesAdapter.notifyDataSetChanged()
    }
}
