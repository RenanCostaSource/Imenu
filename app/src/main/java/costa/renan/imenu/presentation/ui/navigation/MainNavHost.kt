package costa.renan.imenu.presentation.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import costa.renan.imenu.presentation.ui.view.launcher.Launcher
import costa.renan.imenu.presentation.ui.view.menulist.MenuListRoute
import costa.renan.imenu.presentation.ui.view.menulist.MenuListScreen
import costa.renan.imenu.presentation.ui.view.shoppingcart.shoppingcart.ShoppingCartRoute

object Destinations {
    val launcher get() = "launcher"
    val menuList get() = "menuList"
    val shoppingCart get() = "shoppingCart"
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
            MenuListRoute(navController)
        }

        composable(Destinations.shoppingCart) {
            Dialog(onDismissRequest = { navController.popBackStack() }, properties = DialogProperties(decorFitsSystemWindows = true, usePlatformDefaultWidth = false)) {
                ShoppingCartRoute(navController)
            }
        }
    }
}