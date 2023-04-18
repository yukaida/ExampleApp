package com.yukaida.exampleapplication.view.customView

import android.content.Context
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import android.graphics.Canvas
import androidx.appcompat.widget.AppCompatImageView
import com.yukaida.exampleapplication.utils.px
import kotlin.math.min

class SquareImageView(context: Context, attributeSet: AttributeSet) :
    AppCompatImageView(context, attributeSet) {
    val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {


    }

    val widthWant = 300f.px
    val heightWant = 400f.px

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val widthFinal = resolveSize(widthWant.toInt(), widthMeasureSpec)
        val heightFinal = resolveSize(heightWant.toInt(), heightMeasureSpec)

//        val size = min(measuredHeight, measuredWidth)
        setMeasuredDimension(widthFinal, heightFinal)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        with(canvas) {

        }
    }

}