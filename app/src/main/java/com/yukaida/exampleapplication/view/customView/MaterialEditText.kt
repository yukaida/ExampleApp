package com.yukaida.exampleapplication.view.customView

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import android.graphics.Canvas
import android.graphics.Color
import androidx.appcompat.widget.AppCompatEditText
import com.yukaida.exampleapplication.utils.px

class MaterialEditText(context: Context, attributeSet: AttributeSet) :
    AppCompatEditText(context, attributeSet) {

    val topTextSize = 12f.px
    val topTextPadding = 8f.px

    val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        textSize = topTextSize
        color = Color.BLUE
    }

    var topTextAlpha = 0f
        set(value) {
            field = value
            invalidate()
        }

    var isTopTextIsShowing = false

    init {
        setPadding(
            paddingLeft,
            topTextSize.toInt() + topTextPadding.toInt() + paddingTop,
            paddingRight,
            paddingBottom
        )
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        with(canvas) {
            paint.alpha = (topTextAlpha * 0xff).toInt()

            drawText(
                hint.toString(),
                paddingLeft.toFloat() + 2f.px,
                topTextSize + topTextPadding,
                paint
            )

        }
    }

    override fun onTextChanged(
        text: CharSequence?,
        start: Int,
        lengthBefore: Int,
        lengthAfter: Int
    ) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter)
        if (!isTopTextIsShowing && !text.isNullOrBlank()) {
            var alphaAnimation = ObjectAnimator.ofFloat(this, "topTextAlpha", 0f, 1f)
            alphaAnimation.start()
            isTopTextIsShowing=true
        } else if (isTopTextIsShowing && text.isNullOrBlank()) {
            var alphaAnimation = ObjectAnimator.ofFloat(this, "topTextAlpha", 1f, 0f)
            alphaAnimation.start()
            isTopTextIsShowing=false
        }
    }
}