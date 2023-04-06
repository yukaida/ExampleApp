package com.yukaida.exampleapplication.view.customView

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import com.yukaida.exampleapplication.utils.px

//自定义view 饼图
class CustomPieView(context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {

    private val radius = 100f.px
    private val paintCircle = Paint().apply {
        color = Color.WHITE
        strokeWidth = 8f.px
        style = Paint.Style.STROKE
    }

    private val paintArc = Paint().apply {
        color = Color.WHITE
        style = Paint.Style.FILL
    }

    private val arcAngleArray = floatArrayOf(90f, 180f, 90f)
    private val arcColorArray = intArrayOf(Color.RED, Color.GREEN, Color.BLUE)

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        with(canvas) {
            drawCircle(radius, radius, radius, paintCircle)

            var currentAngle = 0f
            val rectF = RectF(0f, 0f, radius * 2, radius * 2)

            arcAngleArray.forEachIndexed { index, indexAngke ->
                drawArc(rectF, currentAngle, indexAngke, true, paintArc.apply {
                    color = arcColorArray[index]
                })
                currentAngle += indexAngke
            }
        }
    }
}