package com.gallery.cat.utils

import android.util.Log
import com.gallery.cat.BuildConfig

object Logger {

    /**
     * check debug flavor
     */
    private fun enableLog():Boolean {
        return BuildConfig.DEBUG
    }

    fun d (tag:String, msg:String) {
        when {
            enableLog() -> Log.d(tag, msg)
        }
    }

    fun e (tag:String, msg:String) {
        when {
            enableLog() -> Log.d(tag, msg)
        }
    }
}