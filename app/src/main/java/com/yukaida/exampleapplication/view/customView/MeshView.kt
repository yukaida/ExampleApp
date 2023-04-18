package com.yukaida.exampleapplication.view.customView

import android.content.Context
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import android.graphics.Canvas
import com.yukaida.exampleapplication.utils.px

class MeshView(context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {
    val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {


    }

    val meshDrawable = MeshDrawable().apply {
//        setBounds(0, 0, width, height)
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        with(canvas) {
            meshDrawable.setBounds(0, 0, width, height)
            meshDrawable.draw(canvas)

//            var offsetX = 0f
//            while (offsetX < width) {
//                canvas.drawLine(offsetX, 0f, offsetX, height.toFloat(), paint)
//                offsetX += 16f.px
//            }
        }
    }

}