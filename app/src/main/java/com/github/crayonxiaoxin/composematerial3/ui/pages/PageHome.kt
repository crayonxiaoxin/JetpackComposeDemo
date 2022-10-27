package com.github.crayonxiaoxin.composematerial3.ui.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.github.crayonxiaoxin.composematerial3.Pages


@Composable
fun PageHome(navController: NavHostController, onThemeChange: () -> Unit = {}) {
    Surface(
        modifier = Modifier.fillMaxSize()
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
                        Icon(imageVector = Icons.Default.Face,
                            contentDescription = "",
                            modifier = Modifier
                                .clickable {
                                    onThemeChange()
                                }
                                .padding(10.dp))
                    },
                )
            }
            // 正文
            Box(Modifier.fillMaxSize()) {
                Column(Modifier.fillMaxWidth()) {
                    Spacer(modifier = Modifier.height(4.dp))

                    HomeItem("1. 返回拦截") {
                        navController.navigate(Pages.BackHandler)
                    }
                    HomeItem("2. remember 和 rememberSaveable") {
                        navController.navigate(Pages.Remember)
                    }
                    HomeItem("3. LocalDensity (sp不随系统变化)") {
                        navController.navigate(Pages.LocalDensity)
                    }
                    HomeItem("4. 使用 Hilt 实现依赖注入") {
                        navController.navigate(Pages.HiltUsage)
                    }
                    HomeItem("5. LargeTopAppBar") {
                        navController.navigate(Pages.LargeAppBar)
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeItem(text: String, onClick: () -> Unit = {}) {
    Card(
        onClick = { onClick() },
        backgroundColor = MaterialTheme.colorScheme.background,
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
        elevation = 2.dp
    ) {
        Text(
            text = text,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        )
    }
}

@Composable
fun SimplePage(title: String, back: () -> Unit = {}, content: @Composable ColumnScope.() -> Unit) {
    Surface(modifier = Modifier.fillMaxSize()) {
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
                SmallTopAppBar(title = { Text(title, fontSize = 16.sp) },
                    colors = TopAppBarDefaults.smallTopAppBarColors(
                        // TopAppBar 用透明色，跟随父容器
                        containerColor = Color.Transparent
                    ),
                    navigationIcon = {
                        Icon(imageVector = Icons.Default.ArrowBack,
                            contentDescription = "",
                            modifier = Modifier
                                // 3. 裁剪，此时可以将 1 和 2 中的效果裁剪
                                .clip(shape = RoundedCornerShape(24.dp))
                                // 2. 设置点击事件，此时点击的范围包括 1 中 padding
                                .clickable {
                                    back()
                                }
                                // 1. 设置间距
                                .padding(10.dp))
                    })
            }
            // 正文
            content()
        }
    }
}