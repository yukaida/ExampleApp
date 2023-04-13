package com.yukaida.exampleapplication.view.customView

import android.content.Context
import android.graphics.Camera
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import android.graphics.Canvas
import com.yukaida.exampleapplication.R
import com.yukaida.exampleapplication.utils.getAvatar
import com.yukaida.exampleapplication.utils.px


//斜边折叠view
class CustomCameraView(context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {
    val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {


    }

    val bitmapWidth = 200f.px
    val bitmapPadding = 100f.px
    val bitmapRes = getAvatar(bitmapWidth.toInt(), R.drawable.cat_square, resources)

    val camera = Camera().apply {
        rotate(30f, 0f, 0f)
        setLocation(0f, 0f, -6 * resources.displayMetrics.density)
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        with(canvas) {
            //上半部分
            save()
            translate(bitmapWidth / 2 + bitmapPadding, bitmapWidth / 2 + bitmapPadding)
            rotate(-30f)
            clipRect(
                -bitmapWidth,
                -bitmapWidth,
                bitmapWidth,
                0f
            )
            rotate(30f)
            translate(-bitmapWidth / 2 - bitmapPadding, -bitmapWidth / 2 - bitmapPadding)
            drawBitmap(bitmapRes, bitmapPadding, bitmapPadding, paint)
            restore()

            //下半部分
            save()

            translate(bitmapWidth / 2 + bitmapPadding, bitmapWidth / 2 + bitmapPadding)
            rotate(-30f)
            camera.applyToCanvas(canvas)
            clipRect(
                -bitmapWidth,
                0f,
                bitmapWidth,
                bitmapWidth
            )
            rotate(30f)
            translate(-bitmapWidth / 2 - bitmapPadding, -bitmapWidth / 2 - bitmapPadding)

            drawBitmap(bitmapRes, bitmapPadding, bitmapPadding, paint)
            restore()
        }
    }

}