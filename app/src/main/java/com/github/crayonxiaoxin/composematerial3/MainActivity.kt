package com.github.crayonxiaoxin.composematerial3

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.view.WindowCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.github.crayonxiaoxin.composematerial3.ui.pages.PageOne
import com.github.crayonxiaoxin.composematerial3.ui.pages.PageTwo
import com.github.crayonxiaoxin.composematerial3.ui.theme.ComposeMaterial3Theme
import com.github.crayonxiaoxin.composematerial3.ui.theme.MyTheme
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val themeViewModel by viewModels<ThemeViewModel>()
            val themeState = themeViewModel.theme.observeAsState(Theme.Light)
            Log.e("TAG", "onCreate: ${themeViewModel.theme.value} ${themeState.value}")
            InitSystemBar(themeState.value.isDark)
            // 切换黑夜模式
//            ComposeMaterial3Theme(darkTheme = themeState.value.isDark) {
//                AnimatedNav(themeViewModel = themeViewModel)
//            }
            // 切换多种自定义主题
            MyTheme(colorScheme = themeState.value.colorScheme) {
                AnimatedNav()
            }
        }
    }

    @Composable
    private fun InitSystemBar(isDarkTheme: Boolean = isSystemInDarkTheme()) {
        // 设置状态栏颜色
        val systemUiController = rememberSystemUiController()
        val useDarkIcons = !isDarkTheme
        DisposableEffect(systemUiController, useDarkIcons) {
            systemUiController.setSystemBarsColor(
                color = Color.Transparent,
                darkIcons = useDarkIcons
            )
            onDispose {}
        }

        // 设置沉浸式状态栏
        WindowCompat.setDecorFitsSystemWindows(window, false)
    }
}

//@Composable
//fun Nav(
//    modifier: Modifier = Modifier,
//    navController: NavHostController = rememberNavController(),
//    startDestination: String = "page1"
//) {
//    NavHost(
//        modifier = modifier,
//        navController = navController,
//        startDestination = startDestination
//    ) {
//        composable("page1") { PageOne(more = { navController.navigate("page2") }) }
//        composable("page2") { PageTwo(back = { navController.popBackStack() }) }
//    }
//}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedNav(
    modifier: Modifier = Modifier,
    themeViewModel: ThemeViewModel = viewModel(),
    navController: NavHostController = rememberAnimatedNavController(),
    startDestination: String = "page1"
) {
    AnimatedNavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination,
        enterTransition = {
            slideIntoContainer(AnimatedContentScope.SlideDirection.Left)
        },
        exitTransition = {
            slideOutOfContainer(AnimatedContentScope.SlideDirection.Left)
        },
        popEnterTransition = {
            slideIntoContainer(AnimatedContentScope.SlideDirection.Right)
        },
        popExitTransition = {
            slideOutOfContainer(AnimatedContentScope.SlideDirection.Right)
        }
    ) {
        composable("page1") {
            PageOne(
                more = { navController.navigate("page2") },
                change = {
                    themeViewModel.toggle()
                }
            )
        }
        composable("page2") {
            PageTwo(back = { navController.popBackStack() })
        }
    }
}

