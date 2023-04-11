package com.yukaida.exampleapplication.view.customView

import android.content.Context
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import android.graphics.Canvas

class MultiLineTextView(context: Context, attributeSet: AttributeSet) :
    View(context, attributeSet) {
    val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {


    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        with(canvas) {

        }
    }

}