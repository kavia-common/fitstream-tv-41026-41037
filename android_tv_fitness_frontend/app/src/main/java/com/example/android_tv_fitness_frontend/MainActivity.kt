package com.example.android_tv_fitness_frontend

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit

/**
 * PUBLIC_INTERFACE
 * MainActivity is the launcher activity for the Android TV app.
 * It sets a content view and attaches a minimal Leanback BrowseSupportFragment
 * so that the app does not show a blank screen on startup.
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
    }
}
