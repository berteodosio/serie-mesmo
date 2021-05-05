package com.berteodosio.seriemesmo.presentation.custom.view

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.res.ResourcesCompat
import com.berteodosio.seriemesmo.R

class MontserratTextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = android.R.attr.textViewStyle
) : AppCompatTextView(context, attrs, defStyleAttr) {

    init {
        this.typeface = ResourcesCompat.getFont(this.context, R.font.montserrat)
    }

}