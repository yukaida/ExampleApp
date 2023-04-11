package com.yukaida.exampleapplication.view.customView

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.text.StaticLayout
import android.text.TextPaint
import android.util.AttributeSet
import android.util.Log
import android.view.View
import com.yukaida.exampleapplication.R
import com.yukaida.exampleapplication.utils.getAvatar
import com.yukaida.exampleapplication.utils.px
//图文混排
class TextRoundImageView(context: Context, attributeSet: AttributeSet) :
    View(context, attributeSet) {

    private val TAG = "TextRoundImageView"

    val textContent =
        "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum."

    val bitmap = getAvatar(100f.px.toInt(), R.drawable.cat_square, resources)

    val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {

    }

    val textPaint = TextPaint().apply {
        color = Color.GREEN
        textSize = 16f.px
    }

//    val staticLayout by lazy {
//        StaticLayout.Builder.obtain(textContent, 0, textContent.length, textPaint, measuredWidth)
//            .build()
//    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        with(canvas) {
//            staticLayout.draw(canvas)
            drawBitmap(
                bitmap,
                width-100f.px,
                80f.px,
                paint
            )

            var line = 0
            var start = 0
            var count: Int
            var textHeight: Float
            while (start < textContent.length) {

                textHeight = -textPaint.fontMetrics.top + (textPaint.fontSpacing) * line

                var widthCurrent =
                    if (textHeight-textPaint.fontSpacing > 180f.px || textHeight < 80f.px) {
                        width
                    } else {
                        (width - 100f.px).toInt()
                    }

                count = textPaint.breakText(
                    textContent,
                    start,
                    textContent.length,
                    true,
                    widthCurrent.toFloat(),
                    null
                )

                drawText(
                    textContent,
                    start,
                    start + count,
                    0f,
                    textHeight,
                    textPaint
                )
                start += count
                line++
            }

        }
    }
}
