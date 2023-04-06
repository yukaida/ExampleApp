package com.yukaida.exampleapplication.view.customView

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.PathDashPathEffect
import android.graphics.PathMeasure
import android.util.AttributeSet
import android.view.View
import com.yukaida.exampleapplication.utils.px
import com.yukaida.exampleapplication.utils.toPx

class TestView(context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val path = Path().apply {
            addArc(0f, 0f, 200f.px, 200f.px, 135f, 270f)

        }

        val dashPath = Path().apply {
            addRect(0f, 0f, 2f.px, 16f.px, Path.Direction.CW)
            addCircle(0f, 0f, 5f.px,Path.Direction.CW)
        }

        val pathWidth = PathMeasure().apply {
            setPath(path,false)
        }.length-2f.px
        val phase=pathWidth/10

        val dashPaint = Paint().apply {
            color = Color.RED
            strokeWidth = 8f.px
            style = Paint.Style.STROKE
            pathEffect = PathDashPathEffect(dashPath, phase, 0f, PathDashPathEffect.Style.ROTATE)
        }

        val paint = Paint().apply {
            color = Color.GREEN
            strokeWidth = 8f.px
            style = Paint.Style.STROKE
        }


        canvas.drawPath(path, paint)
        canvas.drawPath(path, dashPaint)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
    }
}