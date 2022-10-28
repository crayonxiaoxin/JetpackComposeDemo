package com.github.crayonxiaoxin.composematerial3

import android.app.Application
import com.github.crayonxiaoxin.composematerial3.utils.GlobalContext
import com.github.crayonxiaoxin.composematerial3.utils.NotificationUtil
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class HiltApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        GlobalContext provides this
        NotificationUtil.provides(this, "jetpack compose notification")
    }
}