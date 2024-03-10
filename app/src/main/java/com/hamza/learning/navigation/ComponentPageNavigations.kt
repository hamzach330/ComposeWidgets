package com.hamza.learning.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.hamza.learning.ui.mianComponents.MainComponentsList
import com.hamza.learning.ui.subComponents.SubComponentsList
import com.hamza.learning.viewModels.TopBarViewModel

enum class Screen {
    MainComponents,
    SubComponents,
}
sealed class NavigationItem(val route: String) {
    object MainComponents : NavigationItem(Screen.MainComponents.name)
    object SubComponents : NavigationItem(Screen.SubComponents.name)
}
@Composable
fun ComponentsNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = NavigationItem.MainComponents.route,
    topBarModel: TopBarViewModel,
)
{
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(NavigationItem.MainComponents.route)
        {
            MainComponentsList(navController = navController,topBarModel)
        }
        composable(
            NavigationItem.SubComponents.route+"/{mainIndex}",
            arguments = listOf(navArgument("mainIndex") { type = NavType.IntType })
            )
        {
            SubComponentsList(it.arguments!!.getInt("mainIndex"),topBarModel)
        }
    }
}