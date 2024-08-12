package costa.renan.imenu.presentation.ui.view.shoppingcart.shoppingcart

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController

@Composable
fun ShoppingCartRoute(
    navController: NavHostController,
    coordinator: ShoppingCartCoordinator = rememberShoppingCartCoordinator()
) {
    // State observing and declarations
    val uiState by coordinator.screenStateFlow.collectAsState(ShoppingCartState())

    // UI Actions
    val actions = rememberShoppingCartActions(navController, coordinator)

    // UI Rendering
    ShoppingCartScreen(uiState, actions)
}


@Composable
fun rememberShoppingCartActions(
    navController: NavHostController, coordinator: ShoppingCartCoordinator
): ShoppingCartActions {
    return remember(navController, coordinator) {
        ShoppingCartActions(
            onOpen = {
                coordinator.viewModel.getCartItems()
            },
            onDismiss = { navController.popBackStack() },
            onBuy = {
                coordinator.viewModel.buy()
                navController.popBackStack()
            },
            onIncrease = { coordinator.viewModel.increase(it) },
            onDecrease = { coordinator.viewModel.decrease(it) },
            onRemove = { coordinator.viewModel.delete(it) },
        )
    }
}