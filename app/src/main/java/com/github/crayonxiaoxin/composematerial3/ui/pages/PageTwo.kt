package com.github.crayonxiaoxin.composematerial3.ui.pages

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun PageTwo(back: () -> Unit = {}) {
    Surface(modifier = Modifier.fillMaxSize()) {
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

        Column(
            Modifier
                // 防止底部虚拟导航栏遮挡布局
                .navigationBarsPadding()
                .fillMaxSize()
        ) {
            // 顶部导航
            val topBarBgColor = MaterialTheme.colorScheme.background
            Box(
                Modifier
                    // 3. 为导航容器添加阴影，当设置背景颜色时，阴影多出部分将覆盖
                    .shadow(3.dp)
                    // 2. 为导航容器添加背景（最好将 TopAppBar 设为透明色 或 与 TopAppBar 保持一致）
                    .background(color = topBarBgColor)
                    // 1. 防止导航被遮住
                    .statusBarsPadding()
            ) {
                SmallTopAppBar(
                    title = { Text(text = "我是第二页") },
                    colors = TopAppBarDefaults.smallTopAppBarColors(
                        // TopAppBar 用透明色，跟随父容器
                        containerColor = Color.Transparent
                    ),
                    navigationIcon = {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "",
                            modifier = Modifier
                                // 3. 裁剪，此时可以将 1 和 2 中的效果裁剪
                                .clip(shape = RoundedCornerShape(24.dp))
                                // 2. 设置点击事件，此时点击的范围包括 1 中 padding
                                .clickable {
                                    // 点击顶部导航栏返回按钮，直接弹出 dialog
                                    showBackDialog.value = true
                                }
                                // 1. 设置间距
                                .padding(10.dp)
                        )
                    },
                    actions = {
                        Text("返回拦截", modifier = Modifier.padding(horizontal = 8.dp))
                    },
                )
            }
            // 正文
            Box(
                Modifier.fillMaxSize()
            ) {
                LazyColumn() {
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
}