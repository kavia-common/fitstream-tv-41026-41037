package com.example.android_tv_fitness_frontend.ui.prefs.widget

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.view.Gravity
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import com.example.android_tv_fitness_frontend.R

/**
 * PUBLIC_INTERFACE
 * GoldChipView is a TV-ready, focusable selection chip with Titan gold accents and focus ring.
 */
class GoldChipView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : AppCompatTextView(context, attrs, defStyle) {

    private var selectedState = false

    init {
        isClickable = true
        isFocusable = true
        gravity = Gravity.CENTER_VERTICAL
        textSize = 20f
        typeface = Typeface.create(typeface, Typeface.NORMAL)
        setPadding(
            resources.getDimensionPixelSize(R.dimen.tv_pad_xl),
            resources.getDimensionPixelSize(R.dimen.tv_pad_m),
            resources.getDimensionPixelSize(R.dimen.tv_pad_xl),
            resources.getDimensionPixelSize(R.dimen.tv_pad_m),
        )
        background = ContextCompat.getDrawable(context, R.drawable.bg_chip_gold_idle)
        setTextColor(ContextCompat.getColor(context, R.color.tv_primary))
        minHeight = resources.getDimensionPixelSize(R.dimen.tv_touch_min)
    }

    // PUBLIC_INTERFACE
    /** Toggle chip selected state. */
    fun toggleSelected() {
        setSelectedState(!selectedState)
    }

    // PUBLIC_INTERFACE
    /** Set chip selected state explicitly. */
    fun setSelectedState(value: Boolean) {
        selectedState = value
        if (selectedState) {
            background = ContextCompat.getDrawable(context, R.drawable.bg_chip_gold_selected)
            typeface = Typeface.create(typeface, Typeface.BOLD)
        } else {
            background = ContextCompat.getDrawable(context, R.drawable.bg_chip_gold_idle)
            typeface = Typeface.create(typeface, Typeface.NORMAL)
        }
    }

    // PUBLIC_INTERFACE
    /** Return current selected state. */
    fun isSelectedState(): Boolean = selectedState
}
