package com.yukaida.exampleapplication.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.text.StaticLayout
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View

class CustomTextView(context: Context, attrs: AttributeSet) : View(context, attrs) {

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        val path = Path()
        path.addCircle(300f, 450f, 200f, Path.Direction.CW)

        val paint = Paint().apply {
            color = Color.WHITE
            textSize = 32f
            //伪粗体
            isFakeBoldText = true
            //下划线
            isUnderlineText = true
            //倾斜角度
            textSkewX=0.5f
        }

        val textPaint = TextPaint().apply {
            color = Color.WHITE
            textSize = 48f
            //删除线
            isStrikeThruText = true
        }

        canvas?.let {
            val testString =
                "this is test content hello CustomTextView Compose Android Java Kotlin test test test"
            //圆形文字  hOffset vOffset xy偏移量
            it.drawTextOnPath(testString, path, 0f, 0f, paint)

            it.drawText(testString, 0f, 800f, paint)

            //自动换行的textLayout
            val staticLayoutBuild =
                StaticLayout.Builder.obtain(testString, 0, testString.length, textPaint, 900)
            staticLayoutBuild.build().draw(canvas)
//            staticLayoutBuild.build().draw(canvas, path, textPaint, 0)

        }

    }
}