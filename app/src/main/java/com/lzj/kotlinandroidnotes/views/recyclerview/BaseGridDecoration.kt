package com.lzj.kotlinandroidnotes.views.recyclerview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.absoluteValue

class BaseGridDecoration : RecyclerView.ItemDecoration {

    lateinit var mDivider: Drawable
    var mContext: Context
    private val attr = intArrayOf(android.R.attr.listDivider)
    private val mBounds = Rect()
    //最后一个的
    private val mLastBounds = Rect()
    private val spanCount: Int
    private val oration: Int
    private var wInterval = 0
    private var hInterval = 0

    constructor(context: Context, oration: Int, spanCount: Int) : super() {
        val typeArray = context.obtainStyledAttributes(attr)
        mDivider = typeArray.getDrawable(0)!!
        wInterval = mDivider.intrinsicWidth
        hInterval = mDivider.intrinsicHeight
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
        canvas.save()
        val childCount = parent.childCount
        //计算出相邻两个item之间的距离
        var maxHeight = 0
        if (childCount >= 2) {
            val child1 = parent.getChildAt(0)
            parent.getDecoratedBoundsWithMargins(child1, mBounds)
            val child2 = parent.getChildAt(1)
            val mBound2 = Rect()
            parent.getDecoratedBoundsWithMargins(child2, mBound2)
            var len = mBound2.top - mBounds.top
            maxHeight = len.absoluteValue
        } else if (childCount == 1) {
            val child = parent.getChildAt(0)
            parent.getDecoratedBoundsWithMargins(child, mBounds)
            var len = mBounds.top - mBounds.bottom
            maxHeight = len.absoluteValue
        }
        println("height=${maxHeight}")
        for (i in 0 until childCount) {
            //先画上边的线
            val child = parent.getChildAt(i)
            parent.getDecoratedBoundsWithMargins(child, mBounds)
            drawhorHTop(i, canvas)
            //画左边
            drawhorHLeft(i, canvas, maxHeight)
            //画下边的线
            if ((i + 1) % spanCount == 0) {
                drawhorHBottom(i, canvas, maxHeight)
            }
            if (childCount % spanCount > 0) {
                drawhorHBottom(childCount - 1, canvas, maxHeight)
            }
            //画右边
            //最后spanCount个画右边
            if (i >= childCount - spanCount) {
                drawhorHRight(i, canvas, maxHeight)
            }
        }
        canvas.restore()
    }

    private fun drawhorHRight(i: Int, canvas: Canvas, maxHeight: Int) {
        var left = mBounds.right - wInterval
        var top = mBounds.top
        var right = mBounds.right
        var bottom = top + maxHeight
        mDivider.setBounds(left, top, right, bottom)
        mDivider.draw(canvas)
    }

    private fun drawhorHBottom(i: Int, canvas: Canvas, maxHeight: Int) {
        val left = mBounds.left
        val top = mBounds.top + maxHeight - hInterval
        val right = mBounds.right
        val bottom = top + hInterval
        mDivider.setBounds(left, top, right, bottom)
        mDivider.draw(canvas)
    }

    private fun drawhorHLeft(i: Int, canvas: Canvas, maxHeight: Int) {
        var left = mBounds.left - wInterval
        var top = mBounds.top - hInterval
        var right = left + wInterval
        var bottom = top + maxHeight
        if (i < spanCount) {
            left = mBounds.left
            right = left + wInterval
        }
        if (i % spanCount == 0) {
            top = mBounds.top
        }
        mDivider.setBounds(left, top, right, bottom)
        mDivider.draw(canvas)

    }

    private fun drawhorHTop(i: Int, canvas: Canvas) {
        val left = mBounds.left
        var top = mBounds.top - hInterval
        val right = mBounds.right - wInterval
        var bottom = top + hInterval
        if (i % spanCount == 0) {
            top = mBounds.top
            bottom = top + hInterval
        }
        mDivider.setBounds(left, top, right, bottom)
        mDivider.draw(canvas)
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
            val top_bottom = top_top + hInterval
            if (i < spanCount) {
                mDivider.setBounds(top_left, top_top, top_right, top_bottom)
                mDivider.draw(canvas)
            }
            //画下边的线
            val bottom_left = mBounds.left
            val bottom_top = mBounds.bottom - hInterval
            val bottom_right = bottom_left + maxLen
            val bottom_bottom = bottom_top + hInterval
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
                    top_right - wInterval,
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
            val left_right = left_left + wInterval
            val left_bottom = mBounds.bottom
            mDivider.setBounds(left_left, left_top, left_right, left_bottom)
            mDivider.draw(canvas)
        } else {
            //画左边的线
            val left_left = mBounds.left - wInterval
            val left_top = mBounds.top - hInterval
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
            left_ = wInterval
        }
        if (right) {
            right_ = wInterval
        }
        if (top) {
            top_ = hInterval
        }
        if (bottom) {
            bottom_ = hInterval
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
            wInterval = mDivider.intrinsicWidth
            hInterval = mDivider.intrinsicHeight
        }
    }
}