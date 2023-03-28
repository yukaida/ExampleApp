package com.yukaida.exampleapplication.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class CircleProgressView(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private var progress = 0f

    public fun setProgress(progressIn: Float) {
        progress = progressIn
        invalidate()
    }

    public fun getProgress(): Float {
        return progress
    }

    val paint = Paint().apply {
        color = Color.RED
        strokeWidth = 8f
        style = Paint.Style.STROKE
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawArc(0f, 0f, 400f, 400f, -90f, progress, false, paint)
        canvas.drawText(progress.toString(), 160f, 200f, Paint().apply {
            color = Color.WHITE
            strokeWidth = 16f
            textSize = 32f
        })
    }


    public fun upAddProgress(progressIn: Int): Float {
        val progressAdd = when (progressIn) {
            in 0..100 -> {
                progressIn.toFloat() / 100f * 360f
            }
            else -> 0f
        }
        progress += progressAdd
        return progressAdd
    }
}