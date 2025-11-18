package com.example.android_tv_fitness_frontend.ui.prefs

import android.os.Bundle
import android.view.*
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat

import androidx.core.view.setPadding
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.android_tv_fitness_frontend.R
import com.example.android_tv_fitness_frontend.ui.prefs.widget.GoldChipView
import com.example.android_tv_fitness_frontend.ui.shared.MapModalDialog

/**
 * PUBLIC_INTERFACE
 * DietaryPreferencesFragment shows two groups of focusable chips for Preferences and Restrictions,
 * styled with Titan's gold accent and TV focus ring behavior.
 */
class DietaryPreferencesFragment : Fragment() {

    companion object {
        const val TAG = "DietaryPreferencesFragment"

        // PUBLIC_INTERFACE
        /** Factory to create instance. */
        fun newInstance(): DietaryPreferencesFragment = DietaryPreferencesFragment()
    }

    private val prefItems = listOf("Vegetarian", "Vegan", "Keto")
    private val restrictItems =
        listOf("Gluten-Free", "Dairy-Free", "Sugar-Free", "Nut-Free", "Low Sodium", "Halal", "Kosher", "Paleo")

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val root = inflater.inflate(R.layout.fragment_dietary_preferences, container, false)

        val titleLeft = root.findViewById<TextView>(R.id.title_left)
        titleLeft.text = getString(R.string.dietary_prefs_title)

        root.findViewById<TextView>(R.id.subtitle_left).text =
            getString(R.string.dietary_prefs_subtitle)

        root.findViewById<TextView>(R.id.header_prefs).text =
            getString(R.string.dietary_prefs_group_preferences)

        root.findViewById<TextView>(R.id.header_restrict).text =
            getString(R.string.dietary_prefs_group_restrictions)

        val prefsGrid = root.findViewById<LinearLayout>(R.id.grid_prefs)
        val restrictGrid = root.findViewById<LinearLayout>(R.id.grid_restrict)

        // Populate chips
        prefItems.forEachIndexed { idx, label ->
            val chip = GoldChipView(requireContext()).apply {
                text = label
                isFocusable = true
                isFocusableInTouchMode = true
                id = View.generateViewId()
            }
            applyChipFocusBehavior(chip)
            prefsGrid.addView(chip)
            if (idx == 0) chip.requestFocus()
        }

        restrictItems.forEach { label ->
            val chip = GoldChipView(requireContext()).apply {
                text = label
                isFocusable = true
                isFocusableInTouchMode = true
                id = View.generateViewId()
            }
            applyChipFocusBehavior(chip)
            restrictGrid.addView(chip)
        }

        // Open modal via an action hint (map icon area is the "open modal" button)
        val openModal = root.findViewById<View>(R.id.btn_open_modal)
        openModal.isFocusable = true
        openModal.isFocusableInTouchMode = true
        applyFocusRing(openModal)
        openModal.setOnClickListener {
            showMapModal(childFragmentManager)
        }

        return root
    }

    private fun applyChipFocusBehavior(chip: GoldChipView) {
        chip.setOnClickListener {
            chip.toggleSelected()
        }
        applyFocusRing(chip)
    }

    private fun applyFocusRing(view: View) {
        val ring = ContextCompat.getDrawable(requireContext(), R.drawable.tv_focus_ring_gold)
        view.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                v.scaleX = 1.03f
                v.scaleY = 1.03f
                v.foreground = ring
                v.setPadding(resources.getDimensionPixelSize(R.dimen.tv_pad_l))
            } else {
                v.scaleX = 1.0f
                v.scaleY = 1.0f
                v.foreground = null
            }
        }
    }

    private fun showMapModal(fm: FragmentManager) {
        val dialog = MapModalDialog.newInstance()
        dialog.show(fm, MapModalDialog.TAG)
    }
}
