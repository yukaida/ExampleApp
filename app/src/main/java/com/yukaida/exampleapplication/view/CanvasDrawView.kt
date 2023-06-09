package com.yukaida.exampleapplication.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import com.yukaida.exampleapplication.R
import com.yukaida.exampleapplication.utils.toPx

//例举 canvas的一些使用方法
class CanvasDrawView(context: Context, attrs: AttributeSet) : View(context, attrs) {

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.drawCircle(
            100f.toPx(),
            100f.toPx(),
            100f.toPx(),
            Paint().apply {
                color = ContextCompat.getColor(context, R.color.green)
                isAntiAlias = true
            })

        canvas?.drawRoundRect(
            RectF(0f.toPx(), 250f.toPx(), 200f.toPx(), 450f.toPx()),
            16f.toPx(),
            16f.toPx(),
            Paint().apply {
                color = ContextCompat.getColor(context, R.color.purple_700)
                style = Paint.Style.STROKE
                strokeWidth = 20f
                isAntiAlias = true
            })

        canvas?.let {
            with(it) {
                drawArc(
                    RectF(
                        0f.toPx(),
                        250f.toPx(),
                        200f.toPx(),
                        450f.toPx()
                    ),
                    0f,
                    -90f,
                    true,
                    Paint().apply {
                        color = Color.RED
                    }
                )
                drawArc(
                    RectF(
                        0f.toPx(),
                        250f.toPx(),
                        200f.toPx(),
                        450f.toPx()
                    ),
                    90f,
                    90f,
                    false,
                    Paint().apply {
                        color = Color.RED
                        style = Paint.Style.STROKE
                        strokeWidth = 16f
                    }
                )

                drawArc(
                    RectF(
                        0f.toPx(),
                        250f.toPx(),
                        200f.toPx(),
                        450f.toPx()
                    ),
                    0f,
                    90f,
                    false,
                    Paint().apply {
                        color = Color.RED
                        style = Paint.Style.FILL
                        strokeWidth = 16f
                    }
                )

                drawPath(Path().apply {
                    lineTo(0f, 100f.toPx())
                    rLineTo(100f.toPx(), 0f)
                }, Paint().apply {
                    color = Color.BLUE
                    strokeWidth = 32f
                    style = Paint.Style.STROKE
                })

                drawText("CanvasView", 220f.toPx(), 80f, Paint().apply {
                    color = Color.YELLOW
                    textSize=80f
                })

            }


        }


    }
}