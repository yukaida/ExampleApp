package com.yukaida.exampleapplication.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Matrix
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class MatrixView(context: Context, attrs: AttributeSet) : View(context, attrs) {
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.let {
            it.run {
                val matrix = Matrix().apply {
                    reset()
                }
                matrix.postTranslate(100f, 100f)

                save()

                concat(matrix)
//              setMatrix(matrix)
//Canvas.setMatrix(matrix)：用 Matrix 直接替换 Canvas 当前的变换矩阵，即抛弃 Canvas 当前的变换，改用 Matrix 的变换
//Canvas.concat(matrix)：用 Canvas 当前的变换矩阵和 Matrix 相乘，即基于 Canvas 当前的变换，叠加上 Matrix 中的变换。"""

                drawCircle(100f, 100f, 100f, Paint().apply {
                    color = Color.BLUE
                })

//                matrix.setPolyToPoly()

                restore()

            }
        }
    }
}