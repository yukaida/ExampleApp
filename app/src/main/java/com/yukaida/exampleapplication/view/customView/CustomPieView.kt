package com.yukaida.exampleapplication.view.customView

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.core.math.MathUtils
import com.yukaida.exampleapplication.utils.px
import kotlin.math.cos
import kotlin.math.sin

//自定义view 饼图 PieChart
class CustomPieView(context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {
    private  val TAG = "CustomPieView"
    //半径
    private val radius = 100f.px

    //外圈描边宽度 (描边是同时向内外扩散 这里要设成实际想要值的两倍)
    private val circleStrokeWidth = 4f.px

    //描边圆圈中心
    private val circleCenterWidth = radius + circleStrokeWidth

    //饼图 外框矩形 右下角坐标 (宽=高 从左上角开始画 隔出描边距离)
    private val circleBottomRight = radius * 2 + circleStrokeWidth

    //饼图 外框矩形
    private val rectF =
        RectF(circleStrokeWidth, circleStrokeWidth, circleBottomRight, circleBottomRight)

    //角度
    private val arcAngleArray = floatArrayOf(30f, 45f, 75f, 90f, 120f)

    //颜色
    private val arcColorArray =
        intArrayOf(Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW, Color.CYAN)


    private val paintCircle = Paint().apply {
        color = Color.WHITE
        strokeWidth = circleStrokeWidth
        style = Paint.Style.STROKE
        isAntiAlias = true
    }

    private val paintArc = Paint().apply {
        color = Color.WHITE
        style = Paint.Style.FILL
        isAntiAlias = true
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)


        with(canvas) {

            if (circleStrokeWidth > 0f) {
                drawCircle(circleCenterWidth, circleCenterWidth, radius, paintCircle)
            }


            var currentAngle = 0f

            arcAngleArray.forEachIndexed { index, indexAngle ->
                drawArc(rectF, currentAngle, indexAngle, true, paintArc.apply {
                    color = arcColorArray[index]
                })

                val radians=Math.toRadians(currentAngle.toDouble()+indexAngle/2)

                val mathY = sin(radians) * radius
                val mathX = cos(radians) * radius

                Log.d(TAG, "onDraw: currentAngle $currentAngle")
                Log.d(TAG, "onDraw: x $mathX")
                Log.d(TAG, "onDraw: y $mathY")


                drawText("Pie$index",
                    (circleCenterWidth+mathX).toFloat(), (circleCenterWidth + mathY).toFloat(), Paint().apply {
                        textSize=16f.px
                        color=Color.WHITE
                    })


                currentAngle += indexAngle

            }

        }
    }
}