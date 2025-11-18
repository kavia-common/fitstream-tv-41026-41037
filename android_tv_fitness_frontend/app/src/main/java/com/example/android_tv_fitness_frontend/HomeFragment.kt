package com.example.android_tv_fitness_frontend

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.leanback.app.BrowseSupportFragment
import androidx.leanback.widget.ArrayObjectAdapter
import androidx.leanback.widget.HeaderItem
import androidx.leanback.widget.ImageCardView
import androidx.leanback.widget.ListRow
import androidx.leanback.widget.ListRowPresenter
import androidx.leanback.widget.Presenter

/**
 * PUBLIC_INTERFACE
 * HomeFragment shows a minimal Leanback BrowseSupportFragment with a single row of cards.
 * This guarantees a visible, focusable UI on Android TV instead of a blank screen.
 */
class HomeFragment : BrowseSupportFragment() {

    companion object {
        const val TAG = "HomeFragment"

        // PUBLIC_INTERFACE
        /** Factory to create a new instance of HomeFragment. */
        fun newInstance(): HomeFragment = HomeFragment()
    }

    private lateinit var rowsAdapter: ArrayObjectAdapter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupUi()
        setupRows()
    }

    private fun setupUi() {
        title = getString(R.string.app_name)
        brandColor = ContextCompat.getColor(requireContext(), R.color.tv_accent)
        headersState = HEADERS_DISABLED
        isHeadersTransitionOnBackEnabled = false
        badgeDrawable = null
    }

    private fun setupRows() {
        rowsAdapter = ArrayObjectAdapter(ListRowPresenter())
        adapter = rowsAdapter

        val cardPresenter = object : Presenter() {
            override fun onCreateViewHolder(parent: android.view.ViewGroup): ViewHolder {
                val cardView = ImageCardView(parent.context).apply {
                    isFocusable = true
                    isFocusableInTouchMode = true
                    setMainImageDimensions(320, 180)
                }
                return ViewHolder(cardView)
            }

            override fun onBindViewHolder(viewHolder: ViewHolder, item: Any) {
                val card = viewHolder.view as ImageCardView
                val content = item as SampleCard
                card.titleText = content.title
                card.contentText = content.subtitle
                val drawable: Drawable? = ContextCompat.getDrawable(
                    card.context,
                    R.drawable.ic_launcher
                )
                card.mainImage = drawable
                card.setOnClickListener {
                    // No-op placeholder; can navigate to details in future.
                }
            }

            override fun onUnbindViewHolder(viewHolder: ViewHolder) {
                // Nothing to clean up for now.
            }
        }

        val listRowAdapter = ArrayObjectAdapter(cardPresenter).apply {
            add(SampleCard("Daily Workout", "10-min warmup"))
            add(SampleCard("Guided Yoga", "15-min stretch"))
            add(SampleCard("HIIT Blast", "20-min burn"))
            add(SampleCard("Core Strength", "12-min abs"))
        }

        val header = HeaderItem(0, "Featured")
        rowsAdapter.add(ListRow(header, listRowAdapter))
    }

    private data class SampleCard(val title: String, val subtitle: String)
}
