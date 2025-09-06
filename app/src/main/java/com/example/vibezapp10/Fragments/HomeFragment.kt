package com.example.vibezapp10.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.vibezapp10.PlaylistsActivity
import com.example.vibezapp10.R

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
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
            // Handle share
        }

        setupRecyclerViews(view)
    }

    private fun setupRecyclerViews(view: View) {
        val recentActivitiesRecycler = view.findViewById<RecyclerView>(R.id.recent_activities_recycler)
        // Setup adapter and data
    }
}