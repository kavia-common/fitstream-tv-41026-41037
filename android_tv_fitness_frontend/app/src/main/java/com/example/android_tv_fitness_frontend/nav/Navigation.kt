package com.example.android_tv_fitness_frontend.nav

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.commit
import com.example.android_tv_fitness_frontend.HomeFragment
import com.example.android_tv_fitness_frontend.R
import com.example.android_tv_fitness_frontend.ui.login.LoginFragment
import com.example.android_tv_fitness_frontend.ui.prefs.DietaryPreferencesFragment

/**
 * PUBLIC_INTERFACE
 * Navigation helpers for MainActivity and fragments.
 */
object Navigation {

    // PUBLIC_INTERFACE
    /** Navigate to Home screen (Browse/Leanback). */
    fun toHome(activity: FragmentActivity) {
        activity.supportFragmentManager.commit {
            replace(R.id.fragment_container_view, HomeFragment.newInstance(), HomeFragment.TAG)
            addToBackStack(HomeFragment.TAG)
        }
    }

    // PUBLIC_INTERFACE
    /** Navigate to Login screen. */
    fun toLogin(activity: FragmentActivity) {
        activity.supportFragmentManager.commit {
            replace(R.id.fragment_container_view, LoginFragment.newInstance(), LoginFragment.TAG)
            addToBackStack(LoginFragment.TAG)
        }
    }

    // PUBLIC_INTERFACE
    /** Navigate to Dietary Preferences screen. */
    fun toDietaryPreferences(activity: FragmentActivity) {
        activity.supportFragmentManager.commit {
            replace(
                R.id.fragment_container_view,
                DietaryPreferencesFragment.newInstance(),
                DietaryPreferencesFragment.TAG
            )
            addToBackStack(DietaryPreferencesFragment.TAG)
        }
    }

    // PUBLIC_INTERFACE
    /** Convenience extension to pop back stack. */
    fun back(fragment: Fragment) {
        fragment.requireActivity().onBackPressedDispatcher.onBackPressed()
    }
}
