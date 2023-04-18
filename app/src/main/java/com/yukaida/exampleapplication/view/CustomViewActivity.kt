package com.yukaida.exampleapplication.view

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.animation.ValueAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.animation.*
import android.widget.SeekBar
import android.widget.Toast
import androidx.core.view.WindowCompat
import com.yukaida.exampleapplication.R
import com.yukaida.exampleapplication.databinding.ActivityCustomViewBinding
import com.yukaida.exampleapplication.view.touch.TouchTestView
import kotlin.concurrent.thread

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

        //animator
        with(vb) {
//            vb.root.setOnClickListener {
//                Toast.makeText(
//                    this@CustomViewActivity,
//                    "rootView has been clicked",
//                    Toast.LENGTH_SHORT
//                ).show()
//            }


            //属性动画
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
                    Log.d(TAG, "addUpdateListener: ${it.currentPlayTime}")

                }

                objectAnimator.start()

            }


            imageView.setOnClickListener {
                //ViewProperty方式组合动画
//                imageView.animate().scaleY(2f).scaleX(2f).alpha(0f).duration = 2000
//                thread {
//                    Thread.sleep(2000)
//                    runOnUiThread {
//                        imageView.animate().scaleY(1f).scaleX(1f).alpha(1f).duration = 2000
//                    }
//                }

                //PropertyValueHolder 动画holder 一次性注入
//                val animatorHolder = PropertyValuesHolder.ofFloat("scaleX", 2f)
//                val animatorHolder2 = PropertyValuesHolder.ofFloat("scaleY", 2f)
//                val composeAnimator =
//                    ObjectAnimator.ofPropertyValuesHolder(
//                        imageView,
//                        animatorHolder,
//                        animatorHolder2
//                    )
//                composeAnimator.start()

                //AnimatorSet
                val animatorSet = AnimatorSet()
                val objectAnimator1 = ObjectAnimator.ofFloat(imageView, "translationX", 300f, 0f)
                objectAnimator1.interpolator = DecelerateInterpolator()
                val objectAnimator2 =
                    ObjectAnimator.ofFloat(imageView, "translationY", 600f).apply {
                        duration = 2000
                    }

                objectAnimator2.interpolator = BounceInterpolator()
                animatorSet.playSequentially(objectAnimator1, objectAnimator2)
                animatorSet.start()

//                animatorSet.playTogether()


            }


        }
        //layout
        with(vb) {
            seekbarWidth.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(
                    seekBar: SeekBar?,
                    progress: Int,
                    fromUser: Boolean
                ) {
                    Log.d(
                        TAG,
                        "onProgressChanged() called with: seekBar = $seekBar, progress = $progress, fromUser = $fromUser"
                    )
                    if (progress > 10) {
                        measureLayoutView.widthInput = progress * 10
                    }
                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {
                }

                override fun onStopTrackingTouch(seekBar: SeekBar?) {
                }

            })

            seekbarHeight.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(
                    seekBar: SeekBar?,
                    progress: Int,
                    fromUser: Boolean
                ) {
                    Log.d(
                        TAG,
                        "onProgressChanged() called with: seekBar = $seekBar, progress = $progress, fromUser = $fromUser"
                    )
                    if (progress > 10) {
                        measureLayoutView.heightInput = progress * 10
                    }
                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {
                }

                override fun onStopTrackingTouch(seekBar: SeekBar?) {
                }

            })

        }
        //touchEvent
        with(vb) {
            touchTestView.performClick()

            touchTestView.setOnClickListener {
                Log.d(TAG, "onCreate: touchTestView has been click")
            }
//            touchTestView.callOnClick()
        }
    }
}