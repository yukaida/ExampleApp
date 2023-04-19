package com.yukaida.exampleapplication.view.touch

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import android.graphics.Canvas
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.widget.OverScroller
import androidx.core.animation.doOnEnd
import com.yukaida.exampleapplication.R
import com.yukaida.exampleapplication.utils.getAvatar
import com.yukaida.exampleapplication.utils.px
import kotlin.math.max
import kotlin.math.min

//可以放大缩小 拖拽的imageView
class ScaleAbleImageView(context: Context, attributeSet: AttributeSet) :
    View(context, attributeSet) {
    val TAG = this.javaClass.name
    val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {

    }

    val bitmap = getAvatar(200f.px.toInt(), R.drawable.cat, resources)


    //绘制中心偏移量
    var offsetXOrigin = 0f
    var offsetYOrigin = 0f

    //滑动距离
    var offsetXMove = 0f
    var offsetYMove = 0f

    //中心点最大滑动距离
    var maxOffsetY = 0f
    var maxOffsetX = 0f

    //是否处于放大模式
    var isBig = false

    //最大放大比例
    var bigScale = 1f

    //最小放大比例
    var smallScale = 1f

    //缩放动画执行进度
    var scaleFraction = 0f
        set(value) {
            field = value
            invalidate()
        }

    //缩放动画
    var scaleAnimator: ObjectAnimator =
        ObjectAnimator.ofFloat(this, "scaleFraction", 0f, 1f).apply {
            duration = 500
            doOnEnd {
                offsetXMove = 0f
                offsetYMove = 0f
            }
        }

    //惯性滑动
    val overScroller = OverScroller(context)


    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        offsetXOrigin = (width.toFloat() - bitmap.width) / 2
        offsetYOrigin = (height.toFloat() - bitmap.height) / 2

        //长图
        if (bitmap.height > bitmap.width) {
            smallScale = height.toFloat() / bitmap.height
            bigScale = width.toFloat() / bitmap.width
        } else {
            bigScale = height.toFloat() / bitmap.height
            smallScale = width.toFloat() / bitmap.width
        }

        maxOffsetX = (bitmap.width * bigScale - width) / 2
        maxOffsetY = (bitmap.height * bigScale - height) / 2
        Log.d(TAG, "onDraw:  bigScale $bigScale  smallScale $smallScale")

    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        with(canvas) {

            if (!isBig) {
                translate(offsetXMove * scaleFraction, offsetYMove * scaleFraction)
            } else {
                translate(offsetXMove, offsetYMove)
            }


            val scalePresent = smallScale + (bigScale - smallScale) * scaleFraction
            scale(
                scalePresent,
                scalePresent,
                (width / 2).toFloat(),
                (height / 2).toFloat()
            )

            drawBitmap(bitmap, offsetXOrigin, offsetYOrigin, paint)
        }
    }

    val gestureDetector = GestureDetector(context, object : GestureDetector.OnGestureListener {
        override fun onDown(e: MotionEvent): Boolean {
            parent.requestDisallowInterceptTouchEvent(true)
            return true
        }

        override fun onShowPress(e: MotionEvent) {
        }

        override fun onSingleTapUp(e: MotionEvent): Boolean {
            return false
        }

        override fun onScroll(
            e1: MotionEvent,
            e2: MotionEvent,
            distanceX: Float,
            distanceY: Float
        ): Boolean {
            if (!isBig) {
                return false
            }

            offsetXMove -= distanceX
            offsetYMove -= distanceY


            offsetXMove = min(offsetXMove, maxOffsetX)
            offsetXMove = max(-maxOffsetX, offsetXMove)


            offsetYMove = min(offsetYMove, maxOffsetY)
            offsetYMove = max(-maxOffsetY, offsetYMove)

            invalidate()
            return false
        }

        override fun onLongPress(e: MotionEvent) {
        }

        override fun onFling(
            downEvent: MotionEvent,
            event: MotionEvent,
            velocityX: Float,
            velocityY: Float
        ): Boolean {

            overScroller.fling(
                offsetXMove.toInt(),
                offsetYMove.toInt(),
                velocityX.toInt(),
                velocityY.toInt(),
                -maxOffsetX.toInt(),
                maxOffsetX.toInt(),
                -maxOffsetY.toInt(),
                maxOffsetY.toInt(),50,50
            )

            postOnAnimation {
                reSetOffset()
            }

            return false
        }

    }).apply {
        setOnDoubleTapListener(object : GestureDetector.OnDoubleTapListener {
            override fun onSingleTapConfirmed(e: MotionEvent): Boolean {
                return false
            }

            override fun onDoubleTap(e: MotionEvent): Boolean {
                isBig = !isBig
                Log.d(TAG, "onDoubleTap: isBig $isBig")

                if (isBig) {
                    scaleAnimator.start()
                } else {
                    scaleAnimator.reverse()
                }

                return false
            }

            override fun onDoubleTapEvent(e: MotionEvent): Boolean {
                return false
            }

        })
    }

    fun reSetOffset() {
        val isMove = overScroller.computeScrollOffset()
        offsetXMove = overScroller.currX.toFloat()
        offsetYMove = overScroller.currY.toFloat()
        invalidate()
        if (isMove) {
            postOnAnimation {
                reSetOffset()
            }
        }
    }


    override fun onTouchEvent(event: MotionEvent): Boolean {
        return gestureDetector.onTouchEvent(event)
    }
}