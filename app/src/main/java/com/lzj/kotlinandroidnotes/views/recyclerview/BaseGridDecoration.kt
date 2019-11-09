package com.lzj.kotlinandroidnotes.views.recyclerview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class BaseGridDecoration : RecyclerView.ItemDecoration {

    lateinit var mDivider: Drawable
    var mContext: Context
    private val attr = intArrayOf(android.R.attr.listDivider)
    private val mBounds = Rect()
    //最后一个的
    private val mLastBounds = Rect()
    private val spanCount: Int
    private val oration: Int

    constructor(context: Context, oration: Int, spanCount: Int) : super() {
        val typeArray = context.obtainStyledAttributes(attr)
        mDivider = typeArray.getDrawable(0)!!
        typeArray.recycle()
        mContext = context
        this.spanCount = spanCount
        this.oration = oration

    }


    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        if (oration == RecyclerView.VERTICAL) {
            drawVertical(c, parent)
        }
        if (oration == RecyclerView.HORIZONTAL) {
            drawHorzotal(c, parent)
        }
    }

    private fun drawHorzotal(canvas: Canvas, parent: RecyclerView) {
        val childCount = parent.childCount
        //计算出最高的那个item从上到下边的差
        val maxHeight = IntRange(0, childCount - 1).foldIndexed(0) { index, acc, i ->
            val child = parent.getChildAt(i)
            parent.getDecoratedBoundsWithMargins(child, mBounds)
            var len = mBounds.top - mBounds.bottom
            if (len < acc) {
                len = acc
            }
            len
        }
        for (i in 0 until childCount) {
            //先画上边的线
        }
    }

    private fun drawVertical(canvas: Canvas, parent: RecyclerView) {
        canvas.save()
        val childCount = parent.childCount
        //计算出最宽的那个item从左到右边的差
        val maxLen = IntRange(0, childCount - 1).foldIndexed(0) { index, acc, i ->
            val child = parent.getChildAt(i)
            parent.getDecoratedBoundsWithMargins(child, mBounds)
            var len = mBounds.right - mBounds.left
            if (len < acc) {
                len = acc
            }
            len
        }
        for (i in 0 until childCount) {
            val child = parent.getChildAt(i)
            parent.getDecoratedBoundsWithMargins(child, mBounds)
            if (i == childCount - 1) {
                parent.getDecoratedBoundsWithMargins(child, mLastBounds)
            }
            //画左边第一列的线
            drawLeftDecoration(i, canvas)
            //画上边的线
            val top_left = mBounds.left
            val top_top = mBounds.top
            val top_right = mBounds.left + maxLen
            val top_bottom = top_top + mDivider.intrinsicHeight
            if (i < spanCount) {
                mDivider.setBounds(top_left, top_top, top_right, top_bottom)
                mDivider.draw(canvas)
            }
            //画下边的线
            val bottom_left = mBounds.left
            val bottom_top = mBounds.bottom - mDivider.intrinsicHeight
            val bottom_right = bottom_left + maxLen
            val bottom_bottom = bottom_top + mDivider.intrinsicHeight
            mDivider.setBounds(
                bottom_left,
                bottom_top,
                bottom_right,
                bottom_bottom
            )
            mDivider.draw(canvas)

            //画最后一个和最后一列的右边
            if (i == childCount - 1 || (i + 1) % spanCount == 0) {
                mDivider.setBounds(
                    top_right - mDivider.intrinsicWidth,
                    top_top,
                    top_right,
                    bottom_bottom
                )
                mDivider.draw(canvas)
            }

        }
        canvas.restore()
    }

    private fun drawLeftDecoration(i: Int, canvas: Canvas) {
        if (i % spanCount == 0) {
            val left_left = mBounds.left
            val left_top = mBounds.top
            val left_right = left_left + mDivider.intrinsicWidth
            val left_bottom = mBounds.bottom
            mDivider.setBounds(left_left, left_top, left_right, left_bottom)
            mDivider.draw(canvas)
        } else {
            //画左边的线
            val left_left = mBounds.left - mDivider.intrinsicWidth
            val left_top = mBounds.top - mDivider.intrinsicHeight
            val left_right = mBounds.left
            val left_bottom = mBounds.bottom
            mDivider.setBounds(left_left, left_top, left_right, left_bottom)
            mDivider.draw(canvas)
        }
    }


    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val position = parent.getChildAdapterPosition(view)
        if (oration == RecyclerView.VERTICAL) {
            verticalItemOffsets(position, outRect)
        }
        if (oration == RecyclerView.HORIZONTAL) {
            setHorizontalOffsets(position, outRect, parent)
        }


    }

    /**
     * 设置横向的offset
     *
     * @param outRect
     * @param itemPosition
     */
    private fun setHorizontalOffsets(itemPosition: Int, outRect: Rect, parent: RecyclerView) {
        if (itemPosition == 0) {
            //第一个的上下左右都有
            setOffset(outRect, true, true, true, true)
        } else {
            if (itemPosition % spanCount == 0) {
                //这是第一行
                //只有上右边下边
                setOffset(outRect, false, true, true, true)
            } else {
                if (itemPosition < spanCount) {
                    //第一列 左右下
                    setOffset(outRect, true, false, true, true)
                } else {
                    //只有右和下
                    setOffset(outRect, false, false, true, true)
                }

            }
        }
    }

    private fun verticalItemOffsets(position: Int, outRect: Rect) {
        if (position == 0) {
            //第一个的上下左右都有
            setOffset(
                outRect,
                true,
                true,
                true,
                true
            )
        } else {
            if (position % spanCount == 0) {
                //这是第一列
                //只有左右下
                setOffset(outRect, true, false, true, true)
            } else {
                if (position < spanCount) {
                    //第一行
                    setOffset(outRect, false, true, true, true)
                } else {
                    //只有右和下
                    setOffset(outRect, false, false, true, true)
                }
            }
        }
    }

    private fun setOffset(outRect: Rect, left: Boolean, top: Boolean, right: Boolean, bottom: Boolean) {
        var left_ = 0
        var right_ = 0
        var top_ = 0
        var bottom_ = 0
        if (left) {
            left_ = mDivider.intrinsicWidth
        }
        if (right) {
            right_ = mDivider.intrinsicWidth
        }
        if (top) {
            top_ = mDivider.intrinsicHeight
        }
        if (bottom) {
            bottom_ = mDivider.intrinsicHeight
        }

        outRect.set(
            left_,
            top_,
            right_,
            bottom_
        )
    }

    fun setmDivider(itmeDivider: Int) {
        if (mContext != null) {
            mDivider = ContextCompat.getDrawable(mContext!!, itmeDivider)!!
        }
    }
}