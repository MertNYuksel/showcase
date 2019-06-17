package com.kadiraltinok.library.ui.binding

import android.util.TypedValue
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import com.kadiraltinok.library.ui.tooltip.ArrowPosition
import com.kadiraltinok.library.ui.tooltip.TooltipView
import com.kadiraltinok.library.ui.tooltip.TooltipViewState

object BindingSetter {

    @JvmStatic
    @BindingAdapter("tooltipViewState")
    fun TooltipView.setTooltipViewState(tooltipViewState: TooltipViewState) {
        bind(tooltipViewState)
    }

    @JvmStatic
    @BindingAdapter(value = ["bind:applyMargin", "bind:arrowPosition"], requireAll = true)
    fun TooltipView.placeTooltip(margin: Int, arrowPosition: ArrowPosition) {
        if (arrowPosition == ArrowPosition.UP) {
            (layoutParams as? ConstraintLayout.LayoutParams)?.apply {
                topToTop = 0 // parent
                bottomToBottom = -1
                topMargin = margin
            }
        } else if (arrowPosition == ArrowPosition.DOWN) {
            (layoutParams as? ConstraintLayout.LayoutParams)?.apply {
                topToTop = -1
                bottomToBottom = 0 // parent
                bottomMargin = margin
            }
        }
    }

    @JvmStatic
    @BindingAdapter("bind:textSizeInSP")
    fun TextView.setTextSizeInSp(size: Float) {
        setTextSize(TypedValue.COMPLEX_UNIT_SP, size)
    }

    @JvmStatic
    @BindingAdapter(value = ["bind:arrowHorizontalPosition", "bind:arrowPercentage"], requireAll = true)
    fun ImageView.layoutMarginStart(margin: Int, percentage: Int?) {
        (layoutParams as? ConstraintLayout.LayoutParams)?.apply {
            percentage?.let {
                endToEnd = 0
                horizontalBias = (it / 100).toFloat()
            } ?: run {
                this.marginStart = margin
            }
        }
    }
}
