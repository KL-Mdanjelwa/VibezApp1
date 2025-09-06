package com.example.vibezapp10.Fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.example.vibezapp10.DownloadsActivity
import com.example.vibezapp10.FavoritesActivity
import com.example.vibezapp10.PlaylistsActivity
import com.example.vibezapp10.R

/**
 * A simple [Fragment] subclass.
 * Use the [LibraryFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LibraryFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_library, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<CardView>(R.id.downloads_card).setOnClickListener {
            startActivity(Intent(requireContext(), DownloadsActivity::class.java))
        }

        view.findViewById<CardView>(R.id.favorites_card).setOnClickListener {
            startActivity(Intent(requireContext(), FavoritesActivity::class.java))
        }

        view.findViewById<CardView>(R.id.playlists_card).setOnClickListener {
            startActivity(Intent(requireContext(), PlaylistsActivity::class.java))
        }
    }
}