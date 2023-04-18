package com.yukaida.exampleapplication.view.touch

import android.content.Context
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import android.graphics.Canvas
import android.util.Log
import android.view.MotionEvent
import android.widget.Toast
import androidx.compose.ui.platform.LocalDensity

class TouchTestView(context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {
    val tag = "TouchTestView"

    val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {


    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        with(canvas) {

        }
    }


    override fun onTouchEvent(event: MotionEvent): Boolean {
        Log.d(tag, "onTouchEvent: ${event.toString()}")

//        if (event.action == MotionEvent.ACTION_UP) {
//            Toast.makeText(context, "click!", Toast.LENGTH_SHORT).show()
//        }
//
//
//        return true
        return super.onTouchEvent(event)
    }
}