package com.yukaida.exampleapplication.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import androidx.core.view.WindowCompat
import com.yukaida.exampleapplication.R
import com.yukaida.exampleapplication.databinding.ActivityCustomViewBinding

//自定义view at
class CustomViewActivity : AppCompatActivity() {
    private val vb by lazy {
        ActivityCustomViewBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(vb.root)

        val insetsController = WindowCompat.getInsetsController(window, window.decorView)
        insetsController.isAppearanceLightStatusBars = false

        WindowCompat.setDecorFitsSystemWindows(window, false)

        with(vb) {
            customView.setOnClickListener {
                val intent = Intent(MediaStore.ACTION_PICK_IMAGES)
                startActivityForResult(intent, 1)
            }
        }
    }
}