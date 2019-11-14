package com.lzj.kotlinandroidnotes.views

import android.content.Context
import android.graphics.*
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import com.lzj.kotlinandroidnotes.R

/**
 * 梯形View
 */
class TrapezoidalView : View, View.OnClickListener {

    private var isDown: Boolean = false
    override fun onClick(v: View?) {
        isDown = !isDown
       postInvalidate()
    }


    private var viewHight = 0
    private var viewWidth = 0
    val paint: Paint = Paint()
    var downImg: Drawable
    val upImg: Drawable


    //坐标原点
    lateinit var originRect: Pair<Float, Float>
    private var color: Int

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attributeSet: AttributeSet?) : super(context, attributeSet) {

        downImg = ContextCompat.getDrawable(context, R.mipmap.im_arrow_down_menu)!!
        upImg = ContextCompat.getDrawable(context, R.mipmap.im_arrow_up_menu)!!
        val type = context.obtainStyledAttributes(attributeSet, R.styleable.TrapezoidalView)
        color = type.getColor(R.styleable.TrapezoidalView_color, Color.WHITE)
        type.recycle()
        this.setOnClickListener(this)
        initPaint()
    }


    private fun initPaint() {
        paint.setAntiAlias(true);
        //描边
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 2f
        paint.setColor(color)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        viewHight = h
        viewWidth = w
        originRect = Pair(w / 2f, h / 2f)
    }

    override fun onDraw(canvas: Canvas?) {
        //将坐标轴移动到view中间
        if (canvas != null) {
            canvas.save()
            canvas.translate(originRect.first, originRect.second)
            canvas.scale(1f, -1f)//翻转y
            val path = Path()
            paint.alpha = 200
            paint.style = Paint.Style.FILL
            paint.strokeJoin = Paint.Join.ROUND
            path.moveTo(-(originRect.first / 2f), originRect.second)
            path.lineTo((originRect.first / 2f), originRect.second)
            path.lineTo(originRect.first, -originRect.second)
            path.lineTo(-originRect.first, -originRect.second)

            path.close()
            canvas.drawPath(path, paint)

            //画图标
            canvas.scale(0.5f, 0.5f)
            if (isDown) {
                downImg.setBounds(
                    -(originRect.first).toInt(),
                    originRect.second.toInt(),
                    originRect.first.toInt(),
                    -originRect.second.toInt()
                )
                downImg.draw(canvas)
            } else {
                upImg.setBounds(
                    -(originRect.first).toInt(),
                    originRect.second.toInt(),
                    originRect.first.toInt(),
                    -originRect.second.toInt()
                )
                upImg.draw(canvas)
            }
            canvas.restore()
        }


    }
}