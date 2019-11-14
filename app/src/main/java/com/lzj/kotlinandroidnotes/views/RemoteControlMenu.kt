package com.lzj.kotlinandroidnotes.views

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.core.graphics.contains
import kotlin.math.absoluteValue
import androidx.core.view.ViewCompat.getMatrix
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import androidx.core.graphics.minus
import androidx.core.graphics.withMatrix
import com.lzj.kotlinandroidnotes.R
import com.lzj.kotlinandroidnotes.views.utils.*
import java.util.*


class RemoteControlMenu : CustomView {

    var isShowAxis = false
    //中间椭圆的外接矩形
    lateinit var centerCirclerRect: RectF
    //扇形的厚度dp
    var arcHight: Float = 0f
        set(value) {
            field = dp2px(value)
        }
    //大扇形半径
    private lateinit var bigArcRectF: RectF
    //小扇形半径
    private lateinit var smallArcRectF: RectF
    //dp
    var interval: Float = 0f
        set(value) {
            field = dp2px(value)
        }

    var centerRatio: Float = 0.125f
    //中间椭圆
    private var centerCirclerPath: Path
    //上边扇形
    private var topArcPath: Path
    //下边扇形
    private var bottomArcPath: Path
    private var leftArcPath: Path
    private var rightArcPath: Path


    //中心圆的范围
    private var centerRegion: Region
    private var leftRegion: Region
    private var topRegion: Region
    private var rightRegion: Region
    private var bottomRegion: Region


    private lateinit var clipRegion: Region

    private var touchPoint: FloatArray

    private var canvasMatrix: Matrix

    var backColor: Int = 0
    var clickedColor: Int = Color.YELLOW
    private var index: Int = -1

    private var centerColor: Int = 0
    private var leftColor: Int = 0
    private var topColor: Int = 0
    private var rightColor: Int = 0
    private var bottomColor: Int = 0

    init {
        centerCirclerPath = Path()
        topArcPath = Path()
        bottomArcPath = Path()
        leftArcPath = Path()
        rightArcPath = Path()
        touchPoint = floatArrayOf(-1f, -1f)
        centerRegion = Region()
        leftRegion = Region()
        topRegion = Region()
        rightRegion = Region()
        bottomRegion = Region()
        canvasMatrix = Matrix()

    }

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attributeSet: AttributeSet?) : super(context, attributeSet) {
        val type = context.obtainStyledAttributes(attributeSet, R.styleable.RemoteControlMenu)
        backColor = type.getColor(R.styleable.RemoteControlMenu_backColor, Color.parseColor("#4D5266"))
        reinitColor()
        interval = type.getDimension(R.styleable.RemoteControlMenu_interval, 0f)
        arcHight = type.getDimension(R.styleable.RemoteControlMenu_arcHight, 0f)
        centerRatio = type.getFloat(R.styleable.RemoteControlMenu_centerRatio, 0.125f)
        type.recycle()
    }

    private fun reinitColor() {
        centerColor = backColor
        leftColor = backColor
        topColor = backColor
        rightColor = backColor
        bottomColor = backColor
    }

    var clicked: ((Int) -> Unit)? = null
    fun setOnClickedMenuListener(clicked: ((Int) -> Unit)?) {
        this.clicked = clicked
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
//        }
        centerCirclerRect = RectF(-w * centerRatio, -h * centerRatio, w * centerRatio, h * centerRatio)
        clipRegion = Region(-w, -h, w, h)
        // 准备中心椭圆的path
//        centerCirclerPath.addCircle(0f, 0f, centerCirclerRadius, Path.Direction.CCW)

        centerCirclerPath.addOval(centerCirclerRect, Path.Direction.CW)
        centerRegion.setPath(centerCirclerPath, clipRegion)

        //计算画布坐标移动后的矩阵
        val tempMatrix = Matrix()
        tempMatrix.preTranslate(mViewWidth / 2f, mViewHeight / 2f)
        tempMatrix.preScale(1f, -1f)//旋转y轴
        //反转该矩阵，用来将点击的电换算成画布坐标系中的点
        tempMatrix.invert(canvasMatrix)
        println("画布矩阵" + canvasMatrix.toString())

        prepareRectF()

    }


    private fun prepareRectF() {
        //计算大扇形的矩形]
        val size = arcHight + interval
        bigArcRectF = centerCirclerRect.addSize(size)//centerCirclerRadius) +
        //小扇形的矩形
        smallArcRectF = bigArcRectF.minusSize(arcHight)

    }


    private fun dp2px(dp: Float): Float {
        return DimenUtils.let {
            it.getContext(mCurrentContext)
            it.getDpMapPx(dp.toInt())
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {

        when (event!!.action) {
            MotionEvent.ACTION_DOWN -> {
                touchPoint[0] = event.getX()
                touchPoint[1] = event.getY()
                //反矩阵
                canvasMatrix.mapPoints(touchPoint)
                //检测圆心点击
                index = checkClicked(centerRegion, leftRegion, topRegion, rightRegion, bottomRegion, touchPoint)

                invalidateView()

            }
            MotionEvent.ACTION_MOVE -> {
            }
            MotionEvent.ACTION_UP -> {
                callback(index)
                touchPoint[0] = -1f
                touchPoint[1] = -1f
                index = -1
                invalidateView()
            }
            else -> {
            }
        }

        return true
    }

    private fun invalidateView() {
        when (index) {
            0 -> {
                centerColor = clickedColor
            }
            1 -> {
                leftColor = clickedColor
            }
            2 -> {
                topColor = clickedColor
            }
            3 -> {
                rightColor = clickedColor
            }
            4 -> {
                bottomColor = clickedColor
            }
            else -> {
                reinitColor()
            }
        }
        postInvalidate()
    }

    private fun checkClicked(
        centerRegion: Region,
        leftRegion: Region,
        topRegion: Region,
        rightRegion: Region,
        bottomRegion: Region,
        point: FloatArray
    ): Int {
        if (centerRegion.contains(point[0].toInt(), point[1].toInt())) {
            return 0
        }
        if (leftRegion.contains(point[0].toInt(), point[1].toInt())) {
            return 1
        }
        if (topRegion.contains(point[0].toInt(), point[1].toInt())) {
            return 2
        }
        if (rightRegion.contains(point[0].toInt(), point[1].toInt())) {
            return 3
        }
        if (bottomRegion.contains(point[0].toInt(), point[1].toInt())) {
            return 4
        }
        return -1
    }

    private fun callback(index: Int) {
        if (clicked != null) {
            clicked?.let {
                it(index)
            }
        }
    }

    override fun onDraw(canvas: Canvas?) {

        //画辅助坐标轴
        if (canvas != null) {
            canvas.translate(mViewWidth / 2f, mViewHeight / 2f)
            canvas.scale(1f, -1f)//旋转y轴
            //画中间的圆
            drawCenterCircle(canvas)
            //画边缘的四个扇形
            drawEdgeArc(canvas)
            //画辅助坐标
            drawAxis(canvas)
        }
    }

    private fun pathAddArc(
        path: Path,
        bigStartAngle: Float,
        sweepAngle: Float,
        canvas: Canvas,
        apply: () -> Unit
    ) {
        path.addArc(bigArcRectF, bigStartAngle, sweepAngle)
        path.arcTo(smallArcRectF, bigStartAngle + sweepAngle, -sweepAngle)
        path.close()
        apply()
        canvas.drawPath(path, mDefaultPaint)
    }

    private fun drawEdgeArc(canvas: Canvas) {
        mDefaultPaint.style = Paint.Style.FILL

        //准备画上边扇形
        prepareTopArc(canvas)
        //准备画下边扇形
        prepareBottomArc(canvas)
        //画右边的扇形
        prepareRightArc(canvas)
        prepareLeftArc(canvas)
    }

    private fun prepareTopArc(canvas: Canvas) {
        pathAddArc(topArcPath, 40f, 100f, canvas) {
            mDefaultPaint.setColor(topColor)
        }
        topRegion.setPath(topArcPath, clipRegion)
    }

    private fun prepareBottomArc(canvas: Canvas) {
        pathAddArc(bottomArcPath, 220f, 100f, canvas) {
            mDefaultPaint.setColor(bottomColor)
        }
        bottomRegion.setPath(bottomArcPath, clipRegion)
    }

    private fun prepareRightArc(canvas: Canvas) {
        pathAddArc(rightArcPath, 325f, 70f, canvas) {
            mDefaultPaint.setColor(rightColor)
        }
        rightRegion.setPath(rightArcPath, clipRegion)
    }

    private fun prepareLeftArc(canvas: Canvas) {
        pathAddArc(leftArcPath, 505f, 70f, canvas) {
            mDefaultPaint.setColor(leftColor)
        }
        leftRegion.setPath(leftArcPath, clipRegion)
    }

    private fun drawCenterCircle(canvas: Canvas) {
        mDefaultPaint.style = Paint.Style.FILL
        mDefaultPaint.setColor(centerColor)
        canvas.drawPath(centerCirclerPath, mDefaultPaint)
    }

    private fun drawAxis(canvas: Canvas?) {
        // 设置轴长度
        CanvasAidUtils.set2DAxisLength(mViewWidth / 2 * 0.8f, mViewHeight / 2 * 0.8f)
        CanvasAidUtils.setDrawAid(isShowAxis)
        CanvasAidUtils.draw2DCoordinateSpace(canvas)
    }

}