package costa.renan.imenu.presentation.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import costa.renan.imenu.presentation.ui.view.launcher.Launcher
import costa.renan.imenu.presentation.ui.view.menulist.MenuListRoute
import costa.renan.imenu.presentation.ui.view.menulist.MenuListScreen

object Destinations {
    val launcher get() = "launcher"
    val menuList get() = "menuList"
}

@Composable
fun MainNavHost(navController: NavHostController) {

    NavHost(navController = navController, startDestination = Destinations.launcher)
    {
        composable(Destinations.launcher) {
            Launcher{
                navController.navigate(Destinations.menuList)
            }
        }
        composable(Destinations.menuList) {
            MenuListRoute()
        }
    }
}