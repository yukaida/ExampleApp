package com.yukaida.exampleapplication.view.customView

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.text.StaticLayout
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View
import com.yukaida.exampleapplication.R
import com.yukaida.exampleapplication.utils.getAvatar
import com.yukaida.exampleapplication.utils.px

class TextRoundImageView(context: Context, attributeSet: AttributeSet) :
    View(context, attributeSet) {
    val textContent =
        "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum."

    val bitmap = getAvatar(100f.px.toInt(), R.drawable.cat_square, resources)

    val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {


    }

    val textPaint = TextPaint().apply {
        color = Color.GREEN
        textSize = 16f.px
    }


    val staticLayout by lazy {
        StaticLayout.Builder.obtain(textContent, 0, textContent.length, textPaint, measuredWidth)
            .build()
    }

    var start = 0

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)


        with(canvas) {
//            staticLayout.draw(canvas)
            drawBitmap(
                bitmap,
                0f,
                80f.px,
                paint
            )


            val count = textPaint.breakText(textContent, true, width.toFloat(), floatArrayOf(0f))
            drawText(textContent, 0, count, 0f, -textPaint.fontMetrics.top, textPaint)

        }
    }
}
