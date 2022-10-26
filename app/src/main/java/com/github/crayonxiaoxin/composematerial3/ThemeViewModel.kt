package com.github.crayonxiaoxin.composematerial3

import androidx.compose.material3.ColorScheme
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.crayonxiaoxin.composematerial3.ui.theme.BlueColorScheme
import com.github.crayonxiaoxin.composematerial3.ui.theme.DarkColorScheme
import com.github.crayonxiaoxin.composematerial3.ui.theme.GreenColorScheme
import com.github.crayonxiaoxin.composematerial3.ui.theme.LightColorScheme

class ThemeViewModel : ViewModel() {

    private val _theme = MutableLiveData<Theme>(Theme.Light)
    val theme: LiveData<Theme> get() = _theme

    // 更改主题
    fun change(theme: Theme) {
        _theme.value = theme
    }

    // 循环切换主题
    fun toggle() {
        when (_theme.value) {
            is Theme.Dark -> _theme.value = Theme.Light
            is Theme.Light -> _theme.value = Theme.Green
            is Theme.Green -> _theme.value = Theme.Blue
            else -> _theme.value = Theme.Dark
        }
    }
}

// 主题列表
sealed class Theme(
    val colorScheme: ColorScheme = LightColorScheme, // 主题
    val isDark: Boolean = false // 当前主题是否是暗色系
) {
    object Dark : Theme(DarkColorScheme, true)
    object Light : Theme(LightColorScheme, false)
    object Green : Theme(GreenColorScheme, false)
    object Blue : Theme(BlueColorScheme, false)
}