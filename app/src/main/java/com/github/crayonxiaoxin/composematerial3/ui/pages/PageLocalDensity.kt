package com.github.crayonxiaoxin.composematerial3.ui.pages

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * 状态恢复
 *
 * https://developer.android.google.cn/jetpack/compose/state#restore-ui-state
 */
@Composable
fun PageLocalDensity(back: () -> Unit = {}) {
    SimplePage(title = "LocalDensity (sp不随系统变化)", back = back) {
        Box(Modifier.fillMaxSize()) {
            Column(Modifier.fillMaxWidth()) {
                Text(
                    text = "改变系统字体，查看文字大小变化",
                    modifier = Modifier.padding(16.dp)
                )

                Spacer(modifier = Modifier.height(30.dp))

                Text(
                    text = "文字大小随系统变化",
                    fontSize = 18.sp,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(16.dp)
                )

                Spacer(modifier = Modifier.height(30.dp))

                CompositionLocalProvider(
                    // fontScale 缩放因子，=1 表示不缩放
                    LocalDensity provides Density(LocalDensity.current.density, 1f)
                ) {
                    Text(
                        text = "文字大小固定，不随系统变化",
                        fontSize = 18.sp,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
    }
}