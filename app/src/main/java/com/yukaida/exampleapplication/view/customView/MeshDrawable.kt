package com.yukaida.exampleapplication.view.customView

import android.content.Context
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.ColorFilter
import android.graphics.drawable.Drawable
import android.icu.text.ListFormatter.Width
import com.yukaida.exampleapplication.utils.px
import okhttp3.internal.toHexString

//自定义drawable
class MeshDrawable : Drawable() {

    val paint = Paint().apply {
        color = Color.BLUE
        strokeWidth = 4f.px
    }

    override fun draw(canvas: Canvas) {
        var offsetX = 0f
        while (offsetX < bounds.right) {
            canvas.drawLine(offsetX, 0f, offsetX, bounds.height().toFloat(), paint)
            offsetX += 16f.px
        }

        var offsetY = 0f
        while (offsetY < bounds.bottom) {
            canvas.drawLine(0f, offsetY, bounds.right.toFloat(), offsetY, paint)
            offsetY += 16f.px
        }

    }

    override fun setAlpha(alpha: Int) {
        paint.alpha = alpha
    }

    override fun setColorFilter(colorFilter: ColorFilter?) {
        paint.colorFilter = colorFilter
    }

    @Deprecated("Deprecated in Java", ReplaceWith("paint.alpha"))
    override fun getOpacity(): Int {
        return paint.alpha
    }
}