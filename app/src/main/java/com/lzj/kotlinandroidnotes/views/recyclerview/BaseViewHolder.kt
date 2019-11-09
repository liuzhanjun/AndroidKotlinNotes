package com.lzj.kotlinandroidnotes.views.recyclerview

import android.content.ComponentCallbacks
import android.content.Context
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class BaseViewHolder : RecyclerView.ViewHolder, View.OnTouchListener, GestureDetector.OnGestureListener {

    private var touchView: View? = null
    val gestureDetector: GestureDetector

    constructor(itemView: View, mContext: Context) : this(itemView, mContext, null, null) {

    }

    constructor(
        itemView: View,
        mContext: Context,
        clickBack: ((view: View?, postion: Int) -> Unit)?,
        longPress: ((view: View?, postion: Int) -> Unit)?
    ) : super(itemView) {
        itemView.setOnTouchListener(this)
        gestureDetector = GestureDetector(mContext, this)
        this.onClick = clickBack
        this.onLongPress = longPress
    }

    var onClick: ((View?, Int) -> Unit)? = null
    var onLongPress: ((View?, Int) -> Unit)? = null


    override fun onLongPress(e: MotionEvent?) {
        if (onLongPress != null) {
            onLongPress!!(touchView, adapterPosition)
        }
    }

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        touchView = v
        gestureDetector.onTouchEvent(event)
        return true
    }

    override fun onShowPress(e: MotionEvent?) {
    }


    override fun onSingleTapUp(e: MotionEvent?): Boolean {
        if (onClick != null) {
            onClick!!(touchView, adapterPosition)
        }
        return true
    }


    override fun onDown(e: MotionEvent?): Boolean {
        return true
    }

    override fun onFling(e1: MotionEvent?, e2: MotionEvent?, velocityX: Float, velocityY: Float): Boolean {
        return false
    }

    override fun onScroll(e1: MotionEvent?, e2: MotionEvent?, distanceX: Float, distanceY: Float): Boolean {
        return false
    }
}