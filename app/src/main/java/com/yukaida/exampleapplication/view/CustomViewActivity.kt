package com.yukaida.exampleapplication.view

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.animation.*
import androidx.core.view.WindowCompat
import com.yukaida.exampleapplication.R
import com.yukaida.exampleapplication.databinding.ActivityCustomViewBinding

//自定义view at
class CustomViewActivity : AppCompatActivity() {
    private val vb by lazy {
        ActivityCustomViewBinding.inflate(layoutInflater)
    }
    private val TAG = "CustomViewActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(vb.root)

        val insetsController = WindowCompat.getInsetsController(window, window.decorView)
        insetsController.isAppearanceLightStatusBars = false

        WindowCompat.setDecorFitsSystemWindows(window, false)

        with(vb) {
            circleView.setOnClickListener {
//                circleView.upAddProgress(10)
                val objectAnimator =
                    ObjectAnimator.ofFloat(circleView, "progress", 0f, 360f).setDuration(2000)
                objectAnimator.repeatCount = 1
                //重复执行时 使用反向动画
                objectAnimator.repeatMode = ValueAnimator.REVERSE
                objectAnimator.interpolator =
//                    LinearInterpolator()//匀速
                        //抛物线 回拉回弹
//                    AnticipateOvershootInterpolator()
                        //先加速 后减速
//                AccelerateDecelerateInterpolator()
                        //持续加速
//                AccelerateInterpolator()
                        //持续减速
//                DecelerateInterpolator()
                        //落地弹跳
                    BounceInterpolator()


                objectAnimator.addUpdateListener {
                    Log.d(TAG, "addUpdateListener: ${ it.currentPlayTime}")

                }

                objectAnimator.start()

            }

        }

    }
}