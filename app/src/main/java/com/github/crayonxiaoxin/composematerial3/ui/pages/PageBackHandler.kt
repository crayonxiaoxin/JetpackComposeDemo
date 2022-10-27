package com.github.crayonxiaoxin.composematerial3.ui.pages

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun PageBackHandler(back: () -> Unit = {}) {
    val showBackDialog = remember { mutableStateOf(false) }
    // 拦截物理返回按键，弹出 dialog
    BackHandler(true) {
        showBackDialog.value = true
    }

    if (showBackDialog.value) {
        AlertDialog(
            onDismissRequest = {},
            confirmButton = {
                Text(
                    "确定",
                    modifier = Modifier
                        .clickable {
                            showBackDialog.value = false
                            back()
                        }
                        .padding(8.dp)
                )
            },
            dismissButton = {
                Text(
                    "取消",
                    modifier = Modifier
                        .clickable { showBackDialog.value = false }
                        .padding(8.dp)
                )
            },
            title = { Text("提示") },
            text = { Text("您确定返回上一页吗") },
        )
    }

    SimplePage(title = "返回拦截", back = { showBackDialog.value = true }) {
        Box(Modifier.fillMaxSize()) {
            LazyColumn(Modifier.fillMaxWidth()) {
                for (i in 1..20) {
                    item {
                        Text(
                            text = "Hello Jetpack Compose $i!",
                            modifier = Modifier
                                .clickable { }
                                .padding(16.dp)
                        )
                    }
                }
            }
        }
    }
}