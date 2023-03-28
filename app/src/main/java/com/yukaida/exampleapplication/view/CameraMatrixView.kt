package com.yukaida.exampleapplication.view

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Camera
import android.graphics.Canvas
import android.graphics.Matrix
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.yukaida.exampleapplication.R

class CameraMatrixView(context: Context, attrs: AttributeSet) : View(context, attrs) {
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
            camera.getMatrix(matrix)//计算旋转之后的matrix矩阵并赋值 比如这时候矩阵直行顺序是[ 旋转 ]

            matrix.postTranslate((150f + bWidth / 2), bHeight / 2)//向矩阵后插入运算B  得到[ 旋转 B ]
            matrix.preTranslate(-(150f + bWidth / 2), -bHeight / 2)//向矩阵前插入预算A  得到[ A 选择 B]

            //这里其实程序都是按重上往下执行  但是为了便于理解减少心智负担 假定camera canvas坐标系位置不变  看成图像在移动  从下往上推演执行顺序

            canvas.concat(matrix)

            clipRect(150f, bHeight / 2, bWidth + 150f, bHeight)
            drawBitmap(bitmapRes, 150f, 0f, Paint())

            camera.restore()
            restore()


        }

    }
}