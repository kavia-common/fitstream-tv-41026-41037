package com.example.android_tv_fitness_frontend

import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.commit
import com.example.android_tv_fitness_frontend.nav.Navigation

/**
 * PUBLIC_INTERFACE
 * MainActivity is the launcher activity for the Android TV app.
 * It sets a content view and attaches the HomeFragment. Adds quick overlay
 * buttons to navigate to Login and Preferences for demonstration.
 */
class MainActivity : AppCompatActivity() {

    // PUBLIC_INTERFACE
    /** onCreate sets the content view and attaches HomeFragment if first creation. */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Inflate a basic layout so preview is not blank
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                replace(
                    R.id.fragment_container_view,
                    HomeFragment.newInstance(),
                    HomeFragment.TAG
                )
            }
        }

        // Wire overlay demo buttons
        findViewById<ImageButton>(R.id.btn_go_login)?.apply {
            isFocusable = true
            isFocusableInTouchMode = true
            visibility = View.VISIBLE
            setOnClickListener { Navigation.toLogin(this@MainActivity) }
        }
        findViewById<ImageButton>(R.id.btn_go_prefs)?.apply {
            isFocusable = true
            isFocusableInTouchMode = true
            visibility = View.VISIBLE
            setOnClickListener { Navigation.toDietaryPreferences(this@MainActivity) }
        }
    }
}
