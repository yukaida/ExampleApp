package com.yukaida.exampleapplication.view

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Camera
import android.graphics.Canvas
import android.graphics.Matrix
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import androidx.core.view.marginBottom
import com.yukaida.exampleapplication.R

class CameraTestView(context: Context, attrs: AttributeSet) : View(context, attrs) {
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val bitmapRes = BitmapFactory.decodeResource(context.resources, R.drawable.maps)
        val bWidth = bitmapRes.width.toFloat()
        val bHeight = bitmapRes.height.toFloat()

        with(canvas) {
            //上半部分
            save()
            clipRect(150f, 0f, bWidth + 150f, bHeight / 2)
            drawBitmap(bitmapRes, 150f, 0f, Paint())
            restore()

            //下半部分  canvas写法
            val camera = Camera()
            camera.setLocation(0f, 0f, -6 * resources.displayMetrics.density)
            save()
            camera.save()

//            translate((150f + bWidth / 2), bHeight / 2)
//            camera.rotateX(30f)
//            camera.applyToCanvas(canvas)
//            translate(-(150f + bWidth / 2), -bHeight / 2)
//            clipRect(150f, bHeight / 2, bWidth + 150f, bHeight)
//            drawBitmap(bitmapRes, 150f, 0f, Paint())

            //下半部分 Matrix写法
            val matrix = Matrix()
            matrix.reset()
            camera.rotateX(30f)
            camera.getMatrix(matrix)

            matrix.postTranslate((150f + bWidth / 2), bHeight / 2)
            matrix.preTranslate(-(150f + bWidth / 2), -bHeight / 2)

            canvas.concat(matrix)

            clipRect(150f, bHeight / 2, bWidth + 150f, bHeight)
            drawBitmap(bitmapRes, 150f, 0f, Paint())

            camera.restore()
            restore()


        }

    }
}