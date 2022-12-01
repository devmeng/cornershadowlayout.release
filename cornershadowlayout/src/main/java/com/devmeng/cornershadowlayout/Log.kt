package com.devmeng.cornershadowlayout

import android.util.Log

/**
 * Created by devmeng -> MHS
 * Date : 2022/5/30  16:14
 * Version : 1
 */
object Log {
    var TAG: String = "CSL"

    @JvmStatic
    fun d(msg: String) {
        if (BuildConfig.DEBUG) {
            Log.d(TAG, "msg -> $msg")
        }
    }

    @JvmStatic
    fun e(errorMsg: String) {
        if (BuildConfig.DEBUG) {
            Log.e(TAG, "errorMsg -> $errorMsg")
        }
    }

    @JvmStatic
    fun i(info: String) {
        if (BuildConfig.DEBUG) {
            Log.i(TAG, "<[$info]>")
        }
    }

    @JvmStatic
    fun w(warnMsg: String) {
        if (BuildConfig.DEBUG) {
            Log.w(TAG, "<!![$warnMsg]!!>")
        }
    }
}