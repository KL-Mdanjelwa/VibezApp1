package com.example.vibezapp10.Fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.vibezapp10.R

/**
 * A simple [Fragment] subclass.
 * Use the [ProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProfileFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.btn_edit_profile).setOnClickListener {

        }

        view.findViewById<View>(R.id.subscription_card).setOnClickListener {
            // Navigate to SubscriptionActivity or show subscription details
        }

        view.findViewById<View>(R.id.privacy_card).setOnClickListener {
            // Navigate to PrivacySettingsActivity
        }

        view.findViewById<View>(R.id.storage_card).setOnClickListener {
            // Show storage & data details
        }

        view.findViewById<View>(R.id.help_card).setOnClickListener {
            // Navigate to HelpActivity
        }

        view.findViewById<View>(R.id.about_card).setOnClickListener {
            // Show About dialog or activity
        }

        view.findViewById<View>(R.id.logout_card).setOnClickListener {
            // Handle logout logic
        }
    }

}