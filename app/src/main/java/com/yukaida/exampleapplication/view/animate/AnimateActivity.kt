package com.yukaida.exampleapplication.view.animate

import android.animation.Keyframe
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AccelerateInterpolator
import androidx.core.graphics.drawable.toBitmap
import androidx.core.graphics.drawable.toDrawable
import androidx.core.view.WindowCompat
import com.yukaida.exampleapplication.R
import com.yukaida.exampleapplication.databinding.ActivityAnimateBinding
import com.yukaida.exampleapplication.utils.px
import com.yukaida.exampleapplication.view.customView.CustomCameraAnimatorView

class AnimateActivity : AppCompatActivity() {
    val vb by lazy {
        ActivityAnimateBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(vb.root)

        val insetsController = WindowCompat.getInsetsController(window, window.decorView)
        insetsController.isAppearanceLightStatusBars = false

        WindowCompat.setDecorFitsSystemWindows(window, false)

        with(vb) {
            //基础属性动画
            imgCat.setOnClickListener {
                imgCat.animate()
                    .translationX(200f.px)
                    .translationY(200f.px)
                    .alpha(0.5f)
                    .scaleY(1.5f)
                    .scaleXBy(0.5f)//在原值的基础上改变
                    .setStartDelay(500)
                    .duration = 2000

            }

            //圆盘动画
            circleAnimateView.setOnClickListener {
                val objectAnimator =
                    ObjectAnimator.ofFloat(circleAnimateView, "radius", 100f.px).setDuration(2000)
                        .start()
            }

            //AnimatorSet  keyFrame关键帧
            customCameraAnimatorView.setOnClickListener {
//                ObjectAnimator.ofFloat(it, "cameraRotateDegrees", 360f).setDuration(2000).start()
//                ObjectAnimator.ofPropertyValuesHolder()
                val keyFrame1 = Keyframe.ofFloat(0f, 0f.px)
                val keyFrame2 = Keyframe.ofFloat(0.41f, 200f.px)
                val keyFrame3 = Keyframe.ofFloat(0.89f, 150f.px)
                val keyFrame4 = Keyframe.ofFloat(1f, 450f.px)

                val keyFrameHolder = PropertyValuesHolder.ofKeyframe(
                    "translationY",
                    keyFrame1,
                    keyFrame2,
                    keyFrame3,
                    keyFrame4
                )

                val keyFrameAnimator =
                    ObjectAnimator.ofPropertyValuesHolder(it, keyFrameHolder).apply {
                        duration = 3000
                        interpolator = AccelerateInterpolator()
                    }

                keyFrameAnimator.start()

            }
        }

//        val bitmap = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888)
//        val drawable=bitmap.toDrawable(resources)
//        drawable.toBitmap()
//
//        val drawable2=ColorDrawable()
//        drawable2.toBitmap()


    }
}