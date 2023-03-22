package com.yukaida.exampleapplication.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Build
import android.util.AttributeSet
import android.view.View
import androidx.annotation.RequiresApi

//canvas对绘制的辅助
class CanvasTransView(context: Context, attrs: AttributeSet) : View(context, attrs) {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        with(canvas) {
            val paint = Paint().apply {
                color = Color.WHITE
            }
            //范围裁切 裁切方法之后的绘制代码，都会被限制在裁切范围内。
            save()
            clipRect(150f, 100f, 300f, 300f)
//            clipOutRect(150f, 100f, 300f, 300f) //clip范围取反
            //ClipPath
            drawRoundRect(100f, 100f, 300f, 300f, 16f, 16f, paint)
            restore()

            //移动
            save()
            translate(200f, 200f)
            drawRoundRect(100f, 100f, 300f, 300f, 16f, 16f, Paint().apply {
                color = Color.GREEN
            })
            restore()

            //旋转
            save()
//            translate(400f, 400f)
            rotate(45f, 100f, 100f) //旋转角度 xy 旋转轴心坐标
            drawRoundRect(50f, 50f, 150f, 150f, 16f, 16f, Paint().apply {
                color = Color.YELLOW
            })
            restore()

            //缩放
            save()
            scale(1.5f, 1f)
            drawRoundRect(50f, 450f, 150f, 550f, 16f, 16f, Paint().apply {
                color = Color.RED
            })
            restore()

            //错切
            save()
            skew(0.5f, 0f)
            drawRoundRect(50f, 650f, 150f, 750f, 16f, 16f, Paint().apply {
                color = Color.BLUE
            })

        }
    }
}