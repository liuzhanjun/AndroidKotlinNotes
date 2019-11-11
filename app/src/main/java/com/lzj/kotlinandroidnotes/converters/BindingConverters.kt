package com.lzj.kotlinandroidnotes.converters

import android.view.View

object BindingConverters {
    @JvmStatic
    fun BooleanToVisiable(show: Boolean): Int {
        println("======转换${show}")
        if (show) {
            return View.VISIBLE
        }
        return View.GONE;
    }
}