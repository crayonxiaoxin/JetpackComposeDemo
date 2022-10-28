package com.github.crayonxiaoxin.composematerial3.ui.pages

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.crayonxiaoxin.composematerial3.utils.toast

/**
 * 取消 Text 点击效果
 *
 */
@Composable
fun PageClickRipple(back: () -> Unit = {}) {
    SimplePage(title = "取消 Text 点击效果", back = back) {
        Box(Modifier.fillMaxSize()) {
            Column(
                Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = "有点击效果",
                    modifier = Modifier
                        .clickable {
                            toast("有点击效果")
                        }
                        .border(width = 1.dp, color = MaterialTheme.colorScheme.primary)
                        .padding(16.dp)
                        .fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(30.dp))

                Text(
                    text = "没有点击效果",
                    modifier = Modifier
                        .clickable(
                            interactionSource = MutableInteractionSource(),
                            indication = null
                        ) {
                            toast("没有点击效果")
                        }
                        .border(width = 1.dp, color = MaterialTheme.colorScheme.primary)
                        .padding(16.dp)
                        .fillMaxWidth()
                )
            }
        }
    }
}