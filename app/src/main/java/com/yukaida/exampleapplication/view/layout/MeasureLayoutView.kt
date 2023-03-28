package com.yukaida.exampleapplication.view.layout

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.util.AttributeSet
import android.util.Log
import android.view.View

class MeasureLayoutView(context: Context, attrs: AttributeSet) : View(context, attrs) {

    private val TAG = "MeasureLayoutView"

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        Log.d(
            TAG,
            "onMeasure() called with: widthMeasureSpec = $widthMeasureSpec, heightMeasureSpec = $heightMeasureSpec"
        )
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)

        Log.d(TAG, "onLayout: measureWidth = $measuredWidth     measureHeight = $measuredHeight")

        Log.d(
            TAG,
            "onLayout() called with: changed = $changed, left = $left, top = $top, right = $right, bottom = $bottom"
        )
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        Log.d(TAG, "onDraw() called with: canvas = $canvas")
        canvas?.drawColor(Color.GREEN)
    }
}