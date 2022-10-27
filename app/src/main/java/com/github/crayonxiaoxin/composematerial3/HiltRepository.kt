package com.github.crayonxiaoxin.composematerial3

import android.content.Context
import android.widget.Toast


class HiltRepository(private val context: Context) {
    fun toast(msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }
}