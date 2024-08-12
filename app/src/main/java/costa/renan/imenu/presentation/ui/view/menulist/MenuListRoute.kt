package costa.renan.imenu.presentation.ui.view.menulist

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import costa.renan.imenu.presentation.ui.navigation.Destinations

@Composable
fun MenuListRoute(
    navController: NavHostController,
    coordinator: MenuListCoordinator = rememberMenuListCoordinator()
) {
    // State observing and declarations
    val uiState by coordinator.uiState.collectAsState(MenuListState())

    // UI Actions
    val actions = rememberMenuListActions(navController, coordinator)

    // UI Rendering
    MenuListScreen(uiState, actions)
}


@Composable
fun rememberMenuListActions(
    navController: NavHostController,
    coordinator: MenuListCoordinator
): MenuListActions {
    return remember(navController, coordinator) {
        MenuListActions(
            onOpen = {
                coordinator.viewModel.getMenuList()
                coordinator.viewModel.updateCartSize()
            },
            onSelect = { item -> coordinator.viewModel.openSheet(item) },
            onDismiss = { coordinator.viewModel.closeSheet() },
            onIncrease = { coordinator.viewModel.increaseAmount() },
            onDecrease = { coordinator.viewModel.decreaseAmount() },
            onAddToCart = {
                coordinator.viewModel.addToCart()
                coordinator.viewModel.closeSheet()
            },
            onOpenShoppingCart = { navController.navigate(Destinations.shoppingCart) }
        )
    }
}