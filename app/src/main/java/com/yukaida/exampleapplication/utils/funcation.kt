package com.yukaida.exampleapplication.utils

import android.content.Context
import android.content.res.Resources
import android.util.TypedValue

//---------------- dp px 转换
fun Int.toDp(context: Context): Int {
    val scale = context.resources.displayMetrics.density;
    return (this / scale + 0.5f).toInt()
}

fun Int.toPx(context: Context): Int {
    val scale = context.resources.displayMetrics.density;
    return (this * scale + 0.5f).toInt()
}

fun Float.toDp(context: Context): Float {
    val scale = context.resources.displayMetrics.density;
    return this / scale + 0.5f
}

fun Float.toPx(): Float = TypedValue.applyDimension(
    TypedValue.COMPLEX_UNIT_DIP,
    this,
    Resources.getSystem().displayMetrics
)

val Float.px
    get() =
        TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            this,
            Resources.getSystem().displayMetrics
        )



