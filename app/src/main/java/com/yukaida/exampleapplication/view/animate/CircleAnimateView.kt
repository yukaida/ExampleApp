package com.yukaida.exampleapplication.view.animate

import android.content.Context
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import android.graphics.Canvas
import android.graphics.Color
import com.yukaida.exampleapplication.utils.px

class CircleAnimateView(context: Context, attributeSet: AttributeSet) :
    View(context, attributeSet) {
    val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.WHITE
    }

    var radius = 50f.px
        set(value) {
            field = value
            invalidate()
        }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        with(canvas) {
            drawCircle((width / 2).toFloat(), (height / 2).toFloat(), radius, paint)
        }
    }

}