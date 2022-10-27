package com.github.crayonxiaoxin.composematerial3.ui.pages

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.github.crayonxiaoxin.composematerial3.HiltViewModel

/**
 * 使用 Hilt 实现依赖注入 viewModel
 *
 * https://developer.android.google.cn/training/dependency-injection/hilt-android
 *
 * 问题：使用 viewModel() 导致
 *
 * Cannot create an instance of class com.github.crayonxiaoxin.composematerial3.HiltViewModel
 *
 * 解决方法：hiltViewModel()
 *
 * https://developer.android.com/jetpack/androidx/releases/hilt#hilt-navigation-compose-1.0.0
 *
 *
 * 使用方法：
 * 1. 添加依赖
 * 2. 添加 @HiltAndroidApp 到 Application [com.github.crayonxiaoxin.composematerial3.HiltApplication]
 * 3. 添加 @AndroidEntryPoint 到 MainActivity [com.github.crayonxiaoxin.composematerial3.MainActivity]
 * 3. 添加 @HiltViewModel 到 ViewModel [com.github.crayonxiaoxin.composematerial3.HiltViewModel]
 * 4. 添加 @Inject 到 ViewModel，然后为参数进行注入，如果没有参数，可以不标记
 * 5. @Module 中编写以 @provide 标记的方法，为参数提供实例化方法 [com.github.crayonxiaoxin.composematerial3.RepositoryModule]
 * 6. hiltViewModel() 获取 viewModel
 */
@Composable
fun PageHilt(viewModel: HiltViewModel = hiltViewModel(), back: () -> Unit = {}) {
    SimplePage(title = "使用 Hilt 实现依赖注入", back = back) {
        Box(Modifier.fillMaxSize()) {
            Column(Modifier.fillMaxWidth()) {
                Text(
                    text = "通过 Hilt 实例化 viewModel，并调用 repository 其中的方法 toast",
                    modifier = Modifier
                        .clickable {
                            viewModel.repository.toast("通过 Hilt 实例化 viewModel，并调用 repository 其中的方法 toast")
                        }
                        .padding(16.dp)
                )

                Spacer(modifier = Modifier.height(30.dp))
            }
        }
    }
}