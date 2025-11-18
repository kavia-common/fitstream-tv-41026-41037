package com.example.android_tv_fitness_frontend.ui.shared

import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.content.ContextCompat

import androidx.fragment.app.DialogFragment
import com.example.android_tv_fitness_frontend.R

/**
 * PUBLIC_INTERFACE
 * MapModalDialog displays a TV-friendly modal with Gym Hours (left) and a map preview (right).
 * The dialog uses a scrim backdrop and a light card with rounded corners as specified in the design notes.
 */
class MapModalDialog : DialogFragment() {

    companion object {
        const val TAG = "MapModalDialog"

        // PUBLIC_INTERFACE
        /** Factory method. */
        fun newInstance(): MapModalDialog = MapModalDialog()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.TvDialogTheme)
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.apply {
            setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            setBackgroundDrawableResource(android.R.color.transparent)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val root = inflater.inflate(R.layout.dialog_map_modal, container, false)

        val close = root.findViewById<ImageButton>(R.id.btn_close_modal)
        val mapRegion = root.findViewById<View>(R.id.map_region)
        val startNav = root.findViewById<Button>(R.id.btn_start_nav)
        val closeFooter = root.findViewById<Button>(R.id.btn_close_footer)

        // Focus behavior
        listOf<View>(close, mapRegion, startNav, closeFooter).forEach { v ->
            v.isFocusable = true
            v.isFocusableInTouchMode = true
            applyFocusRing(v)
        }

        close.setOnClickListener { dismissAllowingStateLoss() }
        closeFooter.setOnClickListener { dismissAllowingStateLoss() }
        startNav.setOnClickListener {
            // Placeholder: would integrate with full-screen map or intent
            val toastText = root.findViewById<TextView>(R.id.hours_title)
            toastText.text = getString(R.string.map_modal_start_nav_feedback)
        }

        return root
    }

    private fun applyFocusRing(view: View) {
        val ring = ContextCompat.getDrawable(requireContext(), R.drawable.tv_focus_ring_gold)
        view.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                v.scaleX = 1.03f
                v.scaleY = 1.03f
                v.foreground = ring
            } else {
                v.scaleX = 1.0f
                v.scaleY = 1.0f
                v.foreground = null
            }
        }
    }
}
