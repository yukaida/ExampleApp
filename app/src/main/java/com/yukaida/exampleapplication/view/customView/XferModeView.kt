package com.yukaida.exampleapplication.view.customView

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.graphics.RectF
import android.graphics.Xfermode
import android.util.AttributeSet
import android.view.View
import com.yukaida.exampleapplication.R
import com.yukaida.exampleapplication.utils.getAvatar
import com.yukaida.exampleapplication.utils.px

class XferModeView(context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {
    val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
    }

    val xfermodeSRCIN = PorterDuffXfermode(
        PorterDuff.Mode.SRC_IN
    )

    val bitmap = getAvatar(200f.px.toInt(), R.drawable.cat_square, resources)

    val rectF = RectF(0f, 0f, 200f.px, 200f.px)

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        with(canvas) {
            val layerCount = saveLayer(rectF, paint)

            canvas.drawOval(rectF, paint)
            paint.xfermode = xfermodeSRCIN

            drawBitmap(bitmap, 0f, 0f, paint)
            paint.xfermode = null

            restoreToCount(layerCount)
        }

    }
}