package com.example.burger.ui.component

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import com.example.burger.R
import com.example.burger.databinding.ComponentToolbarViewBinding

class ToolbarView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding = ComponentToolbarViewBinding.inflate(LayoutInflater.from(context), this)

    init {
        LayoutInflater.from(context).inflate(R.layout.component_toolbar_view, this)
    }

    fun initToolbar(
        visibleIconLeft: Boolean,
        visibleIconRight: Boolean,
        title: String
    ) = with(binding) {
        isVisible = true
        setupToolbar(visibleIconLeft, visibleIconRight, title)
    }

    private fun setupToolbar(
        visibleIconLeft: Boolean,
        visibleIconRight: Boolean,
        title: String
    ) = with(binding) {
        iconLeft.isVisible = visibleIconLeft
        iconRight.isVisible = visibleIconRight
        textTitle.text = title
    }
}