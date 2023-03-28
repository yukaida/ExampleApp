package com.yukaida.exampleapplication.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout

class DrawSequenceLayout(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawColor(Color.GREEN)

    }

    override fun dispatchDraw(canvas: Canvas) {
        super.dispatchDraw(canvas)



        canvas.drawCircle(100f, 100f, 100f, Paint())
    }

    override fun onDrawForeground(canvas: Canvas) {
        canvas.drawCircle(100f, 100f, 100f, Paint())
        super.onDrawForeground(canvas)
    }
}