package com.yukaida.exampleapplication.view.customView

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Paint
import android.graphics.Path
import android.graphics.Shader
import android.util.AttributeSet
import android.view.View

class CvProgressBar(context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)

        var windowHeight = heightMeasureSpec
        var windowWidth = widthMeasureSpec

        when (heightMode) {
            MeasureSpec.AT_MOST -> {
                windowHeight = 100
            }
            MeasureSpec.EXACTLY -> {

            }
            MeasureSpec.UNSPECIFIED -> {
                windowHeight = 100
            }
        }


        when (widthMode) {
            MeasureSpec.AT_MOST -> {
                windowWidth = context.resources.displayMetrics.widthPixels

            }

        }
        setMeasuredDimension(windowWidth, windowHeight)


    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val offset = 50f
        val barWidth = 600f
        val barHeight = 100f

//        val colorArray = intArrayOf(Color.RED, Color.BLUE)
//        val positionArray= floatArrayOf(0f,1f)

        val colorArray = intArrayOf(Color.RED, Color.BLUE,Color.GRAY,Color.GRAY)
        val positionArray= floatArrayOf(0f,0.5f,0.55f,1f)

        val linearGradient =
            LinearGradient(
                0f,
                0f,
                barWidth,
                0f,
                colorArray,
                positionArray,
                Shader.TileMode.CLAMP
            )
//        val linearGradient =
//            LinearGradient(
//                0f,
//                0f,
//                barWidth,
//                barHeight,
//                Color.RED,
//                Color.BLUE,
//                Shader.TileMode.CLAMP
//            )

        val paint = Paint().apply {
//            color = Color.GREEN
            strokeWidth = 8f
            shader = linearGradient
        }

        val path = Path().apply {
            moveTo(offset, 0f)
            lineTo((offset + barWidth), 0f)
            lineTo(barWidth, barHeight)
            lineTo(0f, barHeight)
            close()
        }

//        val path2 = Path().apply {
//            moveTo(offset, 0f)
//            lineTo((offset + barWidth + 100f), 0f)
//            lineTo(barWidth + 100f, barHeight)
//            lineTo(0f, barHeight)
//            close()
//        }


        canvas.drawPath(path, paint)
//        canvas.drawPath(path2, Paint().apply {
//            color = Color.WHITE
//            style = Paint.Style.STROKE
//        })

    }


}