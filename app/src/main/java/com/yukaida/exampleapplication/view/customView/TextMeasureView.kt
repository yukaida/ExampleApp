package com.yukaida.exampleapplication.view.customView

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.RectF
import android.util.AttributeSet
import android.util.Log
import android.view.View
import com.yukaida.exampleapplication.utils.px

class TextMeasureView(context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {
    private val TAG = "TextMeasureView"

    val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        strokeWidth = 32f
        style = Paint.Style.STROKE
        strokeCap = Paint.Cap.ROUND
    }

    val textPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.GREEN
        textSize = 16f.px
        style = Paint.Style.FILL
        textAlign = Paint.Align.CENTER

    }
    val rectF = RectF(10f.px, 10f.px, 210f.px, 210f.px)

    val textBounds = Rect()
    val textMetrics: Paint.FontMetrics = textPaint.fontMetrics

    val textContent = "当前进度是25%"

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        with(canvas) {
            drawOval(rectF, paint.apply {
                color = Color.GRAY
            })

            drawArc(rectF, 0f, 90f, false, paint.apply {
                color = Color.RED
            })


            textPaint.getTextBounds(textContent, 0, textContent.length, textBounds)

//            drawText(
//                textContent,
//                110f.px,
//                110f.px - ((textBounds.top - textBounds.bottom) / 2),
//                textPaint
//            )
            drawText(
                textContent,
                110f.px,
                110f.px - ((textPaint.ascent() - textPaint.descent()) / 2),
                textPaint
            )

            Log.d(TAG, "onDraw: top ${textMetrics.top}   bottom ${textMetrics.bottom} ")
            drawText(
                "测试文本TestContent",
                0f,
                -textMetrics.top,
                textPaint.apply {
                    textAlign = Paint.Align.LEFT
                })

            textPaint.fontSpacing

        }
    }
}