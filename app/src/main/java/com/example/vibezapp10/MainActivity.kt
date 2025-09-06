package com.example.vibezapp10

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.vibezapp10.Fragments.HomeFragment
import com.example.vibezapp10.Fragments.LibraryFragment
import com.example.vibezapp10.Fragments.ProfileFragment
import com.example.vibezapp10.Fragments.SearchFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        // Default fragment
        loadFragment(HomeFragment())

        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> loadFragment(HomeFragment())
                R.id.nav_search -> loadFragment(SearchFragment())
                R.id.nav_live -> loadFragment(LiveFragment())
                R.id.nav_library -> loadFragment(LibraryFragment())
                R.id.nav_profile -> loadFragment(ProfileFragment())
                else -> false
            }
        }
    }

    private fun loadFragment(fragment: Fragment): Boolean {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
        return true
    }
}
