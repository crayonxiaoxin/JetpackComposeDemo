package com.github.crayonxiaoxin.composematerial3.ui.pages

import androidx.compose.animation.rememberSplineBasedDecay
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PageCollapseTopBar(back: () -> Unit = {}) {
    // 1. 定义滚动行为 scrollBehavior
    val das = rememberSplineBasedDecay<Float>()
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(das)
    Scaffold(
        modifier = Modifier
            .navigationBarsPadding()
            // 2. 父容器设置 nestedScroll
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            // 根据滚动距离改变 TopBar 显示效果
            val topBarBgColor: Color
            val topBarNavIconColor: Color
            val topBarFontSize: TextUnit
            if (scrollBehavior.scrollFraction >= 0.5f) {
                topBarBgColor = MaterialTheme.colorScheme.background
                topBarFontSize = 16.sp
                topBarNavIconColor = MaterialTheme.colorScheme.onBackground
            } else {
                topBarBgColor = MaterialTheme.colorScheme.primary
                topBarFontSize = 20.sp
                topBarNavIconColor = MaterialTheme.colorScheme.background
            }

            Box(
                Modifier
                    .shadow(3.dp)
                    .background(color = topBarBgColor)
                    .statusBarsPadding()
            ) {
                LargeTopAppBar(
                    title = {
                        Text("CollapseTopBar", fontSize = topBarFontSize)
                    },
                    colors = TopAppBarDefaults.smallTopAppBarColors(
                        containerColor = Color.Transparent,
                        navigationIconContentColor = topBarNavIconColor,
                        titleContentColor = topBarNavIconColor
                    ),
                    // 3. 设置滚动行为 scrollBehavior
                    scrollBehavior = scrollBehavior,
                    navigationIcon = {
                        Icon(imageVector = Icons.Default.ArrowBack,
                            contentDescription = "",
                            modifier = Modifier
                                .clip(shape = RoundedCornerShape(24.dp))
                                .clickable {
                                    back()
                                }
                                .padding(10.dp))
                    }
                )
            }
        }
    ) {
        Box(Modifier.fillMaxSize()) {
            LazyColumn(Modifier.fillMaxWidth()) {
                for (i in 1..20) {
                    item {
                        Text(
                            text = "$i!",
                            modifier = Modifier
                                .clickable { }
                                .padding(16.dp)
                                .fillMaxWidth()
                        )
                    }
                }
            }
        }
    }
}