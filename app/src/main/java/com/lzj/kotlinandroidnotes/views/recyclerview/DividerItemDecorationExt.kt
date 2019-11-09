package com.lzj.kotlinandroidnotes.views.recyclerview

import android.content.Context
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import com.lzj.kotlinandroidnotes.R

fun DividerItemDecoration.setmDividerByXml(context: Context, drawalbeRes: Int) {
    val drawable = ContextCompat.getDrawable(context, drawalbeRes)
    if (drawable != null) {
        this.setDrawable(drawable)
    }
}