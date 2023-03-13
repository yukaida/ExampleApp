package com.yukaida.exampleapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.WindowCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.yukaida.exampleapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val vb by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(vb.root)

        val insetsController = WindowCompat.getInsetsController(window, window.decorView)
        insetsController.isAppearanceLightStatusBars = true

        with(vb) {
            rvImage.apply {
                layoutManager = LinearLayoutManager(this@MainActivity)
                adapter = MainTypeImageAdapter(this@MainActivity, mutableListOf("倒影"))
            }
        }
    }
}