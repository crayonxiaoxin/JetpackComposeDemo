package com.github.crayonxiaoxin.composematerial3.utils

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun Context.toast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    CoroutineScope(Dispatchers.Main).launch {
        Toast.makeText(this@toast, message, duration).show()
    }
}

fun Context.toast(@StringRes resId: Int, duration: Int = Toast.LENGTH_SHORT) {
    toast(getString(resId), duration)
}

fun toast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    GlobalContext.current.toast(message, duration)
}

fun toast(@StringRes resId: Int, duration: Int = Toast.LENGTH_SHORT) {
    GlobalContext.current.toast(resId, duration)
}

/**
 * 必须在 Application 中调用 [provides]
 */
@SuppressLint("StaticFieldLeak")
object GlobalContext {

    lateinit var current: Context

    infix fun provides(context: Context) {
        this.current = context
    }
}