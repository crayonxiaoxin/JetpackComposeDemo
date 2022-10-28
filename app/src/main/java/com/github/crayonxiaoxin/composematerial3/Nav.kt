package com.github.crayonxiaoxin.composematerial3

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.github.crayonxiaoxin.composematerial3.ui.pages.*
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

object Pages {
    const val Home = "home"
    const val BackHandler = "back-handler"
    const val Remember = "remember"
    const val LocalDensity = "local-density"
    const val HiltUsage = "hilt-usage"
    const val LargeAppBar = "large-app-bar"
    const val ClickRipple = "click-ripple"
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedNav(
    modifier: Modifier = Modifier,
    onThemeChange: () -> Unit = {},
    navController: NavHostController = rememberAnimatedNavController(),
    startDestination: String = Pages.Home
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
        composable(Pages.Home) {
            PageHome(
                navController,
                onThemeChange = {
                    onThemeChange()
                }
            )
        }
        composable(Pages.BackHandler) {
            PageBackHandler(back = { navController.popBackStack() })
        }
        composable(Pages.Remember) {
            PageRemember(back = { navController.popBackStack() })
        }
        composable(Pages.LocalDensity) {
            PageLocalDensity(back = { navController.popBackStack() })
        }
        composable(Pages.HiltUsage) {
            PageHilt(back = { navController.popBackStack() })
        }
        composable(Pages.LargeAppBar) {
            PageCollapseTopBar(back = { navController.popBackStack() })
        }
        composable(Pages.ClickRipple) {
            PageClickRipple(back = { navController.popBackStack() })
        }
    }
}