package com.example.bookstudy.widget

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.os.Handler
import android.os.Looper
import android.util.AttributeSet
import android.view.View
import kotlin.math.min

@SuppressLint("DrawAllocation")
class PieAnimation : View {
    private var mPaint: Paint = Paint()// 创建一个画笔对象
    private var mDrawingAngle: Float = 0F // 当前绘制的角度
    private var mHandler = Handler(Looper.getMainLooper())// 声明一个处理器对象
    private var isRunning = false // 是否正在播放动画

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        mPaint.color = Color.MAGENTA
    }

    fun start() {
        mDrawingAngle = 0F// 绘制角度调0
        isRunning = true;
        mHandler.post(mRefresh)//立即启动绘图刷新任务

    }

    //是否正在播放
//    fun isRunning() = isRunning

    private val mRefresh:Runnable = object: Runnable{
        @SuppressLint("NewApi")
        override fun run() {
            mDrawingAngle += 3 ;
            if(mDrawingAngle<=270){
                //未绘制完成，最大绘制到270度
                invalidate()//立即刷新视图
                mHandler.postDelayed(this,70)//延迟若干时间后再次启动刷新任务

            }else{//已经绘制完成
                isRunning = false
            }
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if(isRunning){
            //正在模仿饼图动画
            val width = measuredWidth
            val height = measuredHeight
            val diameter = min(width, height)//视图的宽高取较小的那个作为扇形的直径
            //创建扇形的矩形边界
            val rectf = RectF(
                ((width-diameter)/2).toFloat(), ((height-diameter)/2).toFloat(),
                ((width+diameter)/2).toFloat(), ((height+diameter)/2).toFloat()
            )
            //在画布上绘制指定角度的图形。第四个参数为true绘制扇形，为false绘制圆弧
            canvas.drawArc(rectf, 0F,mDrawingAngle,true,mPaint)

        }
    }
}