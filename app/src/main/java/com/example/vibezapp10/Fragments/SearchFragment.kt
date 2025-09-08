package com.example.vibezapp10.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vibezapp10.Adapters.GenreAdapter
import com.example.vibezapp10.Adapters.RecentSearchAdapter
import com.example.vibezapp10.Adapters.SongAdapter
import com.example.vibezapp10.Models.Genre
import com.example.vibezapp10.Models.Song
import com.example.vibezapp10.R

class SearchFragment : Fragment() {

    private lateinit var songAdapter: SongAdapter
    private val searchResults = mutableListOf<Song>()

    private lateinit var searchResultsRecycler: RecyclerView
    private lateinit var genresRecycler: RecyclerView
    private lateinit var recentSearchesRecycler: RecyclerView
    private lateinit var searchView: SearchView
    private lateinit var loadingSection: View
    private lateinit var noResultsSection: View
    private lateinit var searchCategoriesSection: View
    private lateinit var recentSearchesSection: View
    private lateinit var btnClearRecent: Button

    private val recentSearches = mutableListOf<String>()
    private val genres = listOf(
        Genre("Pop", R.drawable.ic_genre_pop),
        Genre("Hip Hop", R.drawable.ic_genre_hiphop),
        Genre("Rock", R.drawable.ic_genre_rock),
        Genre("Jazz", R.drawable.ic_genre_jazz)
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize views
        searchView = view.findViewById(R.id.search_view)
        searchResultsRecycler = view.findViewById(R.id.search_results_recycler)
        genresRecycler = view.findViewById(R.id.genres_grid)
        recentSearchesRecycler = view.findViewById(R.id.recent_searches_recycler)
        loadingSection = view.findViewById(R.id.search_loading)
        noResultsSection = view.findViewById(R.id.no_results_state)
        searchCategoriesSection = view.findViewById(R.id.search_categories_section)
        recentSearchesSection = view.findViewById(R.id.recent_searches_section)
        btnClearRecent = view.findViewById(R.id.btn_clear_recent)

        // Setup RecyclerViews
        songAdapter = SongAdapter(searchResults, onSongClick = { song ->
            // TODO: Navigate to PlayerFragment
        }, onFavoriteClick = { song ->
            // TODO: Toggle favorite
        })
        searchResultsRecycler.layoutManager = LinearLayoutManager(requireContext())
        searchResultsRecycler.adapter = songAdapter

        val genreAdapter = GenreAdapter(genres) { genre ->
            searchView.setQuery(genre.name, true)
        }
        genresRecycler.layoutManager = GridLayoutManager(requireContext(), 2)
        genresRecycler.adapter = genreAdapter

        val recentAdapter = RecentSearchAdapter(recentSearches) { query ->
            searchView.setQuery(query, true)
        }
        recentSearchesRecycler.layoutManager = LinearLayoutManager(requireContext())
        recentSearchesRecycler.adapter = recentAdapter

        btnClearRecent.setOnClickListener {
            recentSearches.clear()
            recentAdapter.notifyDataSetChanged()
        }

        // Handle search
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { performSearch(it, recentAdapter) }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // Optional: live search
                return true
            }
        })
    }

    private fun performSearch(query: String, recentAdapter: RecentSearchAdapter) {
        // Show loading
        loadingSection.visibility = View.VISIBLE
        noResultsSection.visibility = View.GONE
        searchResults.clear()
        songAdapter.notifyDataSetChanged()

        // Hide categories & recent searches
        searchCategoriesSection.visibility = View.GONE
        recentSearchesSection.visibility = View.GONE

        // Add to recent searches
        if (!recentSearches.contains(query)) {
            recentSearches.add(0, query)
            recentAdapter.notifyItemInserted(0)
        }

        // Simulate API call
        val results = ApiPlaceholder.searchSongs(query)
        loadingSection.visibility = View.GONE

        if (results.isEmpty()) {
            noResultsSection.visibility = View.VISIBLE
        } else {
            searchResults.addAll(results)
            songAdapter.notifyDataSetChanged()
        }
    }
}
