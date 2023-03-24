package com.yukaida.exampleapplication.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.yukaida.exampleapplication.R

class CameraView2(context: Context, attrs: AttributeSet) : View(context, attrs) {

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val bitmapRes = BitmapFactory.decodeResource(context.resources, R.drawable.maps)
        val bitmapHeight = bitmapRes.height.toFloat()
        val bitmapWidth = bitmapRes.width.toFloat()

        val camera = Camera()
        camera.save()
        canvas.save()

        camera.setLocation(0f, 0f, -6f * resources.displayMetrics.density)


        //上半部分


        canvas.clipRect(RectF(0f, 0f, bitmapWidth, bitmapHeight/2))
        canvas.drawBitmap(bitmapRes, 0f, 0f, Paint())

        camera.restore()
        canvas.restore()
        //下半部分
        canvas.translate(bitmapWidth / 2, bitmapHeight / 2)

        camera.rotateX(45f)
        camera.applyToCanvas(canvas)

        canvas.translate(-bitmapWidth / 2, -bitmapHeight / 2)

//        canvas.rotate(-45f, bitmapWidth / 2, bitmapHeight / 2)

        canvas.clipRect(RectF(0f, bitmapHeight / 2, bitmapWidth, bitmapHeight))

        canvas.drawBitmap(bitmapRes, 0f, 0f, Paint())

        camera.restore()
        canvas.restore()
    }
}