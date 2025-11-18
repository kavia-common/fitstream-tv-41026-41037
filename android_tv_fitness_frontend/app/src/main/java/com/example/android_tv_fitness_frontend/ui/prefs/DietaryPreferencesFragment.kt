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

        // Clear any existing children (safety on re-inflate)
        prefsGrid.removeAllViews()
        restrictGrid.removeAllViews()

        // Populate chips into rows with exact sizing and spacing
        val prefChips = addChipsInRows(
            parent = prefsGrid,
            items = prefItems,
            columns = 3
        )

        val restrictChips = addChipsInRows(
            parent = restrictGrid,
            items = restrictItems,
            columns = 4
        )

        // D-pad initial focus on first pref chip
        prefChips.firstOrNull()?.requestFocus()

        // Wire modal open button
        val openModal = root.findViewById<View>(R.id.btn_open_modal)
        openModal.isFocusable = true
        openModal.isFocusableInTouchMode = true
        applyFocusRing(openModal)
        openModal.setOnClickListener {
            showMapModal(childFragmentManager)
        }

        // D-pad navigation helpers between groups and CTA
        // First pref chip down -> first restrict chip
        if (prefChips.isNotEmpty() && restrictChips.isNotEmpty()) {
            prefChips[0].nextFocusDownId = restrictChips[0].id
        }
        // Last restrict chip down -> open modal CTA
        if (restrictChips.isNotEmpty()) {
            restrictChips.last().nextFocusDownId = R.id.btn_open_modal
        }

        return root
    }

    private fun addChipsInRows(parent: LinearLayout, items: List<String>, columns: Int): List<GoldChipView> {
        val chips = mutableListOf<GoldChipView>()

        val chipWidth = resources.getDimensionPixelSize(R.dimen.tv_chip_width)
        val chipHeight = resources.getDimensionPixelSize(R.dimen.tv_chip_height)
        val gap = resources.getDimensionPixelSize(R.dimen.tv_grid_gap)

        var row: LinearLayout? = null
        items.forEachIndexed { index, label ->
            if (index % columns == 0) {
                // New row
                row = LinearLayout(requireContext()).apply {
                    orientation = LinearLayout.HORIZONTAL
                    layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    ).apply {
                        if (parent.childCount > 0) {
                            topMargin = gap
                        }
                    }
                }
                parent.addView(row)
            }
            val chip = GoldChipView(requireContext()).apply {
                text = label
                isFocusable = true
                isFocusableInTouchMode = true
                id = View.generateViewId()
                layoutParams = LinearLayout.LayoutParams(chipWidth, chipHeight).apply {
                    if ((index % columns) > 0) {
                        leftMargin = gap
                    }
                }
            }
            applyChipFocusBehavior(chip)
            row?.addView(chip)
            chips.add(chip)
        }

        // D-pad linking across the grid: right/left per row, down/up to the same column in next/prev row
        chips.forEachIndexed { i, chip ->
            val rowIndex = i / columns
            val colIndex = i % columns

            // Next/prev in row
            if (colIndex < columns - 1 && i + 1 < chips.size) {
                chip.nextFocusRightId = chips[i + 1].id
            }
            if (colIndex > 0) {
                chip.nextFocusLeftId = chips[i - 1].id
            }

            // Up/down to same column if exists
            val downIndex = i + columns
            if (downIndex < chips.size) {
                chip.nextFocusDownId = chips[downIndex].id
            }
            val upIndex = i - columns
            if (upIndex >= 0) {
                chip.nextFocusUpId = chips[upIndex].id
            }
        }

        return chips
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
