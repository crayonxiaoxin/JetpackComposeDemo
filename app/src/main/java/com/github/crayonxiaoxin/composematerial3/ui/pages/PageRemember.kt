package com.github.crayonxiaoxin.composematerial3.ui.pages

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * 状态恢复
 *
 * https://developer.android.google.cn/jetpack/compose/state#restore-ui-state
 */
@Composable
fun PageRemember(back: () -> Unit = {}) {
    val number = remember { mutableStateOf(1) }
    val numberSaver = rememberSaveable { mutableStateOf(1) }
    SimplePage(title = "Remember & RememberSaveable", back = back) {
        Box(Modifier.fillMaxSize()) {
            Column(Modifier.fillMaxWidth()) {
                Text(
                    text = "旋转屏幕，查看数据状态",
                    modifier = Modifier.padding(16.dp)
                )

                Text(
                    text = "${number.value}",
                    modifier = Modifier.padding(16.dp)
                )
                Button(onClick = { number.value += 1 }) {
                    Text(text = "remember +1")
                }

                Spacer(modifier = Modifier.height(30.dp))

                Text(
                    text = "${numberSaver.value}",
                    modifier = Modifier
                        .clickable { }
                        .padding(16.dp)
                )
                Button(onClick = { numberSaver.value += 1 }) {
                    Text(text = "rememberSaveable +1")
                }

                Text(
                    text = "remember 和 rememberSaveable 区别：",
                    modifier = Modifier.padding(16.dp)
                )
                Text(
                    text = "1. rememberSaveable 可以在旋转屏幕保持状态",
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                Text(
                    text = "2. rememberSaveable 可以在重新创建 activity 和进程后保持状态",
                    modifier = Modifier.padding(horizontal = 16.dp)
                )

            }
        }
    }
}