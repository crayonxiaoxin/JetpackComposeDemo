package com.github.crayonxiaoxin.composematerial3.ui.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun PageOne(back: () -> Unit = {}, change: () -> Unit = {}, more: () -> Unit = {}) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        // Modifier.systemBarsPadding() 状态栏 + 虚拟导航
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
                    title = { Text(text = "Compose Android") },
                    colors = TopAppBarDefaults.smallTopAppBarColors(
                        // TopAppBar 用透明色，跟随父容器
                        containerColor = Color.Transparent
                    ),
                    actions = {
                        Icon(
                            imageVector = Icons.Default.Face,
                            contentDescription = "",
                            modifier = Modifier
                                .clickable {
                                    change()
                                }
                                .padding(10.dp)
                        )
                        Icon(
                            imageVector = Icons.Default.MoreVert,
                            contentDescription = "",
                            modifier = Modifier
                                .clickable { more() }
                                .padding(10.dp)
                        )
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
                                text = "Hello Android $i!",
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