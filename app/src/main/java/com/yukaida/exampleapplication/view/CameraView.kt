package com.yukaida.exampleapplication.view

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Camera
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.View
import com.yukaida.exampleapplication.R

class CameraView(context: Context, attrs: AttributeSet) : View(context, attrs) {

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        with(canvas) {
            val bitmapRes =
//                BitmapFactory.decodeResource(context.resources, R.drawable.cat_square)
                BitmapFactory.decodeResource(context.resources, R.drawable.maps)

            val bitmapWidth = bitmapRes.width.toFloat()
            val bitmapHeight = bitmapRes.height.toFloat()

            save()
            val camera = Camera()
            camera.save()

            clipRect(0f, bitmapHeight / 2, bitmapWidth + 100f + 50f, bitmapHeight + 50f)

            //z轴默认-8f
            camera.setLocation(0f, 0f, -6 * resources.displayMetrics.density)
//            camera.setLocation(0f,0f,-8f)

//            camera.rotateY(45f)
//            camera.rotateZ(45f)

            Log.d(TAG, "locationZ: ${camera.locationZ}")



            canvas.translate(
                (bitmapRes.width / 2).toFloat() + 150f,
                (bitmapRes.height / 2).toFloat()
            )

            camera.rotateX(30f)
            camera.applyToCanvas(this)

            canvas.translate(
                -(bitmapRes.width / 2).toFloat() - 150f,
                -(bitmapRes.height / 2).toFloat()
            )

//            canvas.drawRect(100f, 100f, 200f, 200f, Paint().apply {
//                color = Color.WHITE
//            })

            canvas.drawBitmap(bitmapRes, 150f, 0f, Paint())


            camera.restore()
            restore()


            drawPoint(
                (bitmapRes.width / 2).toFloat(),
                (bitmapRes.height / 2).toFloat(),
                Paint().apply {
                    strokeWidth = 16f
                    color = Color.GREEN
                })

            drawLine(0f, bitmapHeight / 2, bitmapWidth, bitmapHeight / 2, Paint().apply {
                color = Color.GREEN
                strokeWidth = 8f
            })
            drawLine(bitmapWidth / 2, 0f, bitmapWidth / 2, bitmapHeight, Paint().apply {
                color = Color.GREEN
                strokeWidth = 8f
            })
//----------------------------------------------------------
            save()
            camera.save()
            clipRect(0f, 0f, bitmapWidth + 100f + 100f, bitmapHeight / 2)

            canvas.translate(
                (bitmapRes.width / 2).toFloat() + 150f,
                (bitmapRes.height / 2).toFloat()
            )

            camera.rotateX(-15f)
            camera.applyToCanvas(this)

            canvas.translate(
                -(bitmapRes.width / 2).toFloat() - 150f,
                -(bitmapRes.height / 2).toFloat()
            )

            canvas.drawBitmap(bitmapRes, 150f, 0f, Paint())

            camera.restore()
            canvas.restore()

        }
    }

    companion object {
        private const val TAG = "CameraView"
    }
}