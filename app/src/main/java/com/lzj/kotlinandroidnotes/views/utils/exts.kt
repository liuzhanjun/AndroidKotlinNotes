package com.lzj.kotlinandroidnotes.views.utils

import android.graphics.RectF

//该方法的坐标系是数学上的坐标系，与手机坐标系不同
//该坐标系在View中间，x轴方向不变y，轴向上
fun RectF.addSize(size: Float): RectF {
    return RectF(this.left - size, this.top - size, this.right + size, this.bottom + size)
}

//该方法的坐标系是数学上的坐标系，与手机坐标系不同
//该坐标系在View中间，x轴方向不变y，轴向上
fun RectF.minusSize(size: Float): RectF {
    return RectF(this.left + size, this.top + size, this.right - size, this.bottom - size)
}