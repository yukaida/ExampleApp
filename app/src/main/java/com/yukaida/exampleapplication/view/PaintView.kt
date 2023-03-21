package com.yukaida.exampleapplication.view

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.BitmapShader
import android.graphics.BlurMaskFilter
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.ComposeShader
import android.graphics.LinearGradient
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.RadialGradient
import android.graphics.Rect
import android.graphics.Shader
import android.graphics.SweepGradient
import android.util.AttributeSet
import android.view.View
import com.yukaida.exampleapplication.R

class PaintView(context: Context, attrs: AttributeSet) : View(context, attrs) {

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        with(canvas) {
            //线性渐变
            val linearGradient =
                LinearGradient(0f, 0f, 200f, 0f, Color.RED, Color.BLUE, Shader.TileMode.REPEAT)
            //Clamp钳位模式  Mirror镜像 Repeat重复

            drawRect(Rect(0, 0, 400, 200), Paint().apply {
                shader = linearGradient
            })

            drawText("Kotlin Android PaintView", 0f, 300f, Paint().apply {
                shader = linearGradient
                textSize = 70f
                //背景渲染
                setShadowLayer(16f, 0f, 0f, Color.WHITE)
                //高斯模糊遮罩
                maskFilter = BlurMaskFilter(8f, BlurMaskFilter.Blur.INNER)
            })

            //辐射渐变
            val radialGradient =
                RadialGradient(200f, 550f, 50f, Color.RED, Color.BLUE, Shader.TileMode.MIRROR)
            drawCircle(200f, 550f, 200f, Paint().apply {
                shader = radialGradient
            })
            drawCircle(200f, 1000f, 200f, Paint().apply {
                shader = radialGradient
            })

            //扫描渐变
            val sweepGradient = SweepGradient(200f, 1500f, Color.RED, Color.BLUE)
            drawCircle(200f, 1500f, 200f, Paint().apply {
                shader = sweepGradient
            })


            //Bitmap 着色器
            val bitmapShader = BitmapShader(
                BitmapFactory.decodeResource(context.resources, R.drawable.cat_square),
                Shader.TileMode.CLAMP,
                Shader.TileMode.CLAMP
            )
            drawCircle(200f, 1500f, 100f, Paint().apply {
                shader = bitmapShader
            })

            //ComposeShader混合着色器  两个shader结合
            val composeShader = ComposeShader(sweepGradient, bitmapShader, PorterDuff.Mode.SRC_OVER)
        }
    }
}