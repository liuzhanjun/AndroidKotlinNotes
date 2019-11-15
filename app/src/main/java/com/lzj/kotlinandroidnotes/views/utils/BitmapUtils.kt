package com.lzj.kotlinandroidnotes.views.utils

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.PixelFormat
import android.graphics.drawable.Drawable


class BitmapUtils {
    companion object {
        fun drawableToBitmap(drawable: Drawable, w: Int, h: Int): Bitmap {
            // 取 drawable 的颜色格式
            val config = if (drawable.opacity != PixelFormat.OPAQUE)
                Bitmap.Config.ARGB_8888
            else
                Bitmap.Config.RGB_565
            // 建立对应 bitmap
            val bitmap = Bitmap.createBitmap(w, h, config)
            // 建立对应 bitmap 的画布
            val canvas = Canvas(bitmap)
            drawable.setBounds(0, 0, w, h)
            // 把 drawable 内容画到画布中
            drawable.draw(canvas)
            return bitmap
        }
    }
}