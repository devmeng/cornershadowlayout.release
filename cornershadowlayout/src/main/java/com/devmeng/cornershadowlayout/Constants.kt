@file: JvmName("Constants")

package com.devmeng.cornershadowlayout

import android.content.Context
import android.util.DisplayMetrics
import android.util.TypedValue

fun px2dp(context: Context, px: Int): Float {
    val density = displayMetrics(context).density
    return (px / density + 0.5f)
}

fun dp2px(context: Context, dp: Int): Float {
    val density = displayMetrics(context).density
    return (dp * density + 0.5f)
}

fun dpTypedValue(context: Context, value: Int): Float {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        value.toFloat(),
        displayMetrics(context)
    )
}

fun pxTypedValue(context: Context, value: Int): Float {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_PX,
        value.toFloat(),
        displayMetrics(context)
    )
}

fun displayMetrics(context: Context): DisplayMetrics {
    return context.resources.displayMetrics
}