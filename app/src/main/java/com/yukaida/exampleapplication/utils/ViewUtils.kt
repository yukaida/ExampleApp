package com.yukaida.exampleapplication.utils

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.yukaida.exampleapplication.R


fun getAvatar(width: Int, resID: Int,resources: Resources): Bitmap {
    val options = BitmapFactory.Options()
    options.inJustDecodeBounds = true
    BitmapFactory.decodeResource(
        resources,
        resID,
        options
    )
    options.inJustDecodeBounds = false

    options.inDensity = options.outWidth
    options.inTargetDensity = width
    return BitmapFactory.decodeResource(
        resources,
        resID,
        options
    )
}
