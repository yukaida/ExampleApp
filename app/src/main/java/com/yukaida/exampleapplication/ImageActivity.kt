package com.yukaida.exampleapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.WindowCompat
import com.yukaida.exampleapplication.databinding.ActivityImageBinding

class ImageActivity : AppCompatActivity() {
    private val vb by lazy {
        ActivityImageBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(vb.root)

        val insetsController = WindowCompat.getInsetsController(window, window.decorView)
        insetsController.isAppearanceLightStatusBars = false

        WindowCompat.setDecorFitsSystemWindows(window, false)


    }
}